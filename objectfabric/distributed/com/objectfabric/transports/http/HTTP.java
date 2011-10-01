/**
 * Copyright (c) ObjectFabric Inc. All rights reserved.
 *
 * This file is part of ObjectFabric (objectfabric.com).
 *
 * ObjectFabric is licensed under the Apache License, Version 2.0, the terms
 * of which may be found at http://www.apache.org/licenses/LICENSE-2.0.html.
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 * WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package com.objectfabric.transports.http;

import java.nio.ByteBuffer;

import com.objectfabric.Privileged;
import com.objectfabric.misc.Bits;
import com.objectfabric.misc.Debug;
import com.objectfabric.misc.List;
import com.objectfabric.misc.NIOManager;
import com.objectfabric.misc.PlatformAdapter;
import com.objectfabric.misc.PlatformConcurrentMap;
import com.objectfabric.misc.PlatformConcurrentQueue;
import com.objectfabric.misc.Queue;
import com.objectfabric.misc.RuntimeIOException;
import com.objectfabric.misc.UID;
import com.objectfabric.transports.filters.Filter;
import com.objectfabric.transports.filters.FilterFactory;
import com.objectfabric.transports.http.HTTPSession.FilterState;

/**
 * Detects if a incoming socket connection uses the HTTP protocol, and implements a
 * compatible bidirectional transport in this case (Comet, maybe WebSocket in the future).
 * Otherwise the client is assumed to support direct socket connections, and the filter
 * removes itself from the chain.
 */
public class HTTP extends Privileged implements FilterFactory {

    static final byte[] POST_CHECK = "POST ".getBytes();

    static final byte[] NEW_LINE = "\r\n".getBytes();

    private final PlatformConcurrentMap<UID, HTTPSession> _sessions = new PlatformConcurrentMap<UID, HTTPSession>();

    private final boolean _enableCrossOriginResourceSharing;

    static final ByteBuffer OK, OK_CROSS_ORIGIN, OK_CHUNKED, OK_CHUNKED_CROSS_ORIGIN, TIMEOUT, HEARTBEAT, EOF;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: Keep-Alive\r\n");
        sb.append("Content-Length: 0\r\n\r\n");
        // TODO direct, not yet as TLS needs same buffer type on each end
        OK = ByteBuffer.allocate(sb.length());
        OK.put(sb.toString().getBytes());
        OK.flip();

        if (Debug.ENABLED)
            Debug.assertion(OK.remaining() == sb.length());

        sb.setLength(sb.length() - 2); // Remove last line
        sb.append("Access-Control-Allow-Origin: *\r\n\r\n");
        OK_CROSS_ORIGIN = ByteBuffer.allocate(sb.length());
        OK_CROSS_ORIGIN.put(sb.toString().getBytes());
        OK_CROSS_ORIGIN.flip();

        if (Debug.ENABLED)
            Debug.assertion(OK_CROSS_ORIGIN.remaining() == sb.length());

        sb.setLength(0);
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Connection: Keep-Alive\r\n");
        sb.append("Transfer-Encoding: chunked\r\n");
        sb.append("Content-Type: text/plain\r\n\r\n");
        OK_CHUNKED = ByteBuffer.allocate(sb.length());
        OK_CHUNKED.put(sb.toString().getBytes());
        OK_CHUNKED.flip();

        if (Debug.ENABLED)
            Debug.assertion(OK_CHUNKED.remaining() == sb.length());

        sb.setLength(sb.length() - 2); // Remove last line
        sb.append("Access-Control-Allow-Origin: *\r\n\r\n");
        OK_CHUNKED_CROSS_ORIGIN = ByteBuffer.allocate(sb.length());
        OK_CHUNKED_CROSS_ORIGIN.put(sb.toString().getBytes());
        OK_CHUNKED_CROSS_ORIGIN.flip();

        if (Debug.ENABLED)
            Debug.assertion(OK_CHUNKED_CROSS_ORIGIN.remaining() == sb.length());

        sb.setLength(0);
        sb.append("HTTP/1.1 408 Request Timeout\r\n");
        sb.append("Connection: Keep-Alive\r\n\r\n");
        TIMEOUT = ByteBuffer.allocate(sb.length());
        TIMEOUT.put(sb.toString().getBytes());
        TIMEOUT.flip();

        if (Debug.ENABLED)
            Debug.assertion(TIMEOUT.remaining() == sb.length());

        sb.setLength(0);
        sb.append("400\r\n");

        for (int i = 0; i < 0x400; i++)
            sb.append((char) 0);

        sb.append("\r\n");
        HEARTBEAT = ByteBuffer.allocate(sb.length());
        HEARTBEAT.put(sb.toString().getBytes());
        HEARTBEAT.flip();

        if (Debug.ENABLED)
            Debug.assertion(HEARTBEAT.remaining() == sb.length());

        sb.setLength(0);
        sb.append("0\r\n\r\n");
        EOF = ByteBuffer.allocate(sb.length());
        EOF.put(sb.toString().getBytes());
        EOF.flip();

        if (Debug.ENABLED) {
            Debug.assertion(EOF.remaining() == sb.length());
            NIOManager.setDebugPurposesMinBufferLength(POST_CHECK.length);
        }
    }

    /**
     * By default Cross-Origin Resource Sharing is enabled.
     */
    public HTTP() {
        this(true);
    }

    /**
     * Cross-Origin Resource Sharing (http://www.w3.org/TR/access-control) allows a
     * browser to connect to multiple domains. A web page can be fetched from one domain,
     * e.g. Amazon S3, while its JavaScript connects to an ObjectFabric server hosted on
     * another domain.
     */
    public HTTP(boolean enableCrossOriginResourceSharing) {
        _enableCrossOriginResourceSharing = enableCrossOriginResourceSharing;
    }

    public Filter createFilter(boolean clientSide) {
        if (clientSide)
            throw new UnsupportedOperationException();

        return new HTTPFilter();
    }

    final void remove(HTTPSession session) {
        _sessions.remove(new UID(session.getId()));
    }

    final class HTTPFilter implements Filter {

        public final FilterState IDLE, RUNNING;

        private List<FilterFactory> _factories;

        private int _filterIndex;

        private Filter _previous, _next;

        //

        private int _httpCheckIndex, _readHeadersIndex, _offset;

        private byte _type;

        private int _decoderBits, _decoderIndex;

        private final byte[] _id = new byte[PlatformAdapter.UID_BYTES_COUNT];

        private HTTPSession _session;

        private final PlatformConcurrentQueue<ByteBuffer> _responses = new PlatformConcurrentQueue<ByteBuffer>();

        public HTTPFilter() {
            IDLE = new FilterState(this, FilterState.STATE_IDLE);
            RUNNING = new FilterState(this, FilterState.STATE_RUNNING);
        }

        public void init(List<FilterFactory> factories, int index, boolean clientSide) {
            if (Debug.ENABLED)
                Debug.assertion(!clientSide);

            _factories = factories;
            _filterIndex = index;
        }

        public Filter getPrevious() {
            return _previous;
        }

        public void setPrevious(Filter value) {
            _previous = value;
        }

        public Filter getNext() {
            return _next;
        }

        public void setNext(Filter value) {
            _next = value;
        }

        //

        public void close() {
            getPrevious().close();
        }

        public void requestWrite() {
            getPrevious().requestWrite();
        }

        //

        public void onReadStarted() {
        }

        public void onReadStopped(Exception e) {
            if (_session != null)
                _session.onFilterDisconnected(this);
        }

        public void onWriteStarted() {
        }

        public void onWriteStopped(Exception e) {
            if (_session != null)
                _session.onFilterDisconnected(this);
        }

        public void read(ByteBuffer buffer) {
            if (_httpCheckIndex == 0) {
                /*
                 * Check if connection is HTTP. If not, remove filter and call startup
                 * methods that were skipped.
                 */
                boolean post = true;

                if (buffer.remaining() < POST_CHECK.length)
                    post = false;
                else
                    while (_httpCheckIndex < POST_CHECK.length)
                        if (buffer.get(buffer.position() + _httpCheckIndex) != POST_CHECK[_httpCheckIndex++])
                            post = false;

                if (!post) {
                    Filter next = _factories.get(_filterIndex + 1).createFilter(false);
                    next.init(_factories, _filterIndex + 1, false);
                    next.setPrevious(_previous);
                    _previous.setNext(next);

                    next.onReadStarted();
                    next.onWriteStarted();

                    // Assume still on first packet
                    next.read(buffer);

                    // Done, switched to direct socket, this filter will be GCed
                    return;
                }
            }

            // Search for empty line to skip headers

            while (_readHeadersIndex < 4) {
                if (buffer.remaining() == 0)
                    return;

                switch (_readHeadersIndex) {
                    case 0: {
                        if (buffer.get() == NEW_LINE[0])
                            _readHeadersIndex++;

                        break;
                    }
                    case 2: {
                        if (buffer.get() == NEW_LINE[0])
                            _readHeadersIndex++;
                        else
                            _readHeadersIndex = 0;

                        break;
                    }
                    case 1:
                    case 3: {
                        if (buffer.get() == NEW_LINE[1])
                            _readHeadersIndex++;
                        else
                            _readHeadersIndex = 0;

                        break;
                    }
                }
            }

            // Read fields

            if (buffer.remaining() == 0)
                return;

            if (_offset == CometTransport.FIELD_TYPE) {
                byte type = buffer.get();

                if (type < CometTransport.MIN && type > CometTransport.MAX) {
                    // Invalid type, probably not an ObjectFabric connection, close socket
                    throw new RuntimeIOException();
                }

                _type = type;
                _offset++;
            }

            if (buffer.remaining() == 0)
                return;

            if (_offset == CometTransport.FIELD_IS_ENCODED) {
                _decoderIndex = buffer.get() == 0 ? -1 : 0;
                _decoderBits = 0;
                _offset++;
            }

            // Decode if needed

            if (_decoderIndex >= 0) {
                int position = buffer.position();

                for (int i = buffer.position(); i < buffer.limit(); i++) {
                    if (_decoderIndex == 0) {
                        _decoderBits = buffer.get(i);
                        _decoderIndex = 7;
                    } else {
                        byte b = buffer.get(i);
                        buffer.put(position++, Bits.get(_decoderBits, --_decoderIndex) ? (byte) (-b - 1) : b);
                    }
                }

                buffer.limit(position);
            }

            if (buffer.remaining() == 0)
                return;

            if (_type == CometTransport.CONNECTION) {
                boolean first = _offset == CometTransport.FIELD_ID;

                if (first) {
                    byte[] id = PlatformAdapter.createUID();
                    _session = new HTTPSession(HTTP.this, id, _enableCrossOriginResourceSharing);
                    _sessions.put(new UID(id), _session);
                    _session.init(_factories, _filterIndex, false);
                    _offset++;
                }

                _session.readInitialConnection(this, buffer, first);
            } else {
                int idRemaining = CometTransport.FIELD_ID + PlatformAdapter.UID_BYTES_COUNT - _offset;

                if (idRemaining > 0) {
                    int length = Math.min(buffer.remaining(), idRemaining);
                    buffer.get(_id, _offset - CometTransport.FIELD_ID, length);
                    _offset += length;

                    if (_offset == CometTransport.FIELD_ID + PlatformAdapter.UID_BYTES_COUNT) {
                        _session = _sessions.get(new UID(_id));

                        /*
                         * Session does not exist, either invalid request or timeout.
                         */
                        if (_session == null) {
                            // Ignore rest of request
                            buffer.position(buffer.limit());
                            enqueue(TIMEOUT);
                            endRequest();
                            requestWrite();
                            return;
                        }

                        requestWrite();
                    }
                }

                if (_type == CometTransport.CLIENT_TO_SERVER) {
                    boolean done = _session.readAndReturnIfDone(this, buffer);

                    if (done) {
                        // Reset for next POST

                        if (_enableCrossOriginResourceSharing)
                            enqueue(OK_CROSS_ORIGIN);
                        else
                            enqueue(OK);

                        endRequest();
                        requestWrite();
                    }
                }
            }
        }

        public void enqueue(ByteBuffer buffer) {
            _responses.add(buffer);
        }

        public boolean write(ByteBuffer buffer, Queue<ByteBuffer> headers) {
            for (;;) {
                ByteBuffer response = _responses.poll();

                if (response == null)
                    break;

                addHeader(response, buffer, headers);
            }

            if (_type == CometTransport.CONNECTION || _type == CometTransport.SERVER_TO_CLIENT)
                return _session.write(this, buffer, headers);

            return true;
        }

        public void endRequest() {
            _httpCheckIndex = 0;
            _readHeadersIndex = 0;
            _offset = 0;

            /*
             * Needed to prevent writes after a request has been terminated when it has
             * reached HTTPSession.MAX_RESPONSE_LENGTH.
             */
            _type = 0;
        }
    }

    static void addHeader(ByteBuffer header, ByteBuffer buffer, Queue<ByteBuffer> headers) {
        headers.add(header.duplicate());
        buffer.limit(buffer.limit() - header.remaining());
    }

    // Debug

    @Override
    protected void assertIdle() {
        Debug.assertion(_sessions.size() == 0);
    }
}
