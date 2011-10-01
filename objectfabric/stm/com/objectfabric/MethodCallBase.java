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

package com.objectfabric;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.objectfabric.misc.AsyncCallback;
import com.objectfabric.misc.CompletedFuture;

abstract class MethodCallBase extends FutureWithCallback {

    private static final Future CANCELLED = new CompletedFuture<Void>(null);

    private static final Future CANCELLED_INTERRUPT = new CompletedFuture<Void>(null);

    private volatile Future _userFuture;

    private static final AtomicReferenceFieldUpdater<MethodCallBase, Future> _userFutureUpdater;

    static {
        _userFutureUpdater = AtomicReferenceFieldUpdater.newUpdater(MethodCallBase.class, Future.class, "_userFuture");
    }

    @SuppressWarnings("unchecked")
    protected MethodCallBase(AsyncCallback callback, AsyncOptions options) {
        super(callback, options);
    }

    @Override
    public final void set(Object value) {
        set(value, false);
    }

    @SuppressWarnings("unchecked")
    protected void set(Object value, boolean direct) {
        super.set(value);
    }

    @Override
    public final void setException(Exception e) {
        setException(e, false);
    }

    protected void setException(Exception e, boolean direct) {
        super.setException(e);
    }

    /**
     * If your method code is represented by a future, assign it using this method so the
     * system can cancel the computation if requested by the caller.
     */
    public final void setFuture(Future future) {
        for (;;) {
            Future current = _userFuture;

            if (current != null) {
                if (current == CANCELLED) {
                    future.cancel(false);
                    break;
                }

                if (current == CANCELLED_INTERRUPT) {
                    future.cancel(true);
                    break;
                }

                throw new RuntimeException(Strings.ONLY_ONCE);
            }

            if (_userFutureUpdater.compareAndSet(this, null, future))
                break;
        }
    }

    @Override
    public final boolean cancel(boolean mayInterruptIfRunning) {
        for (;;) {
            Future current = _userFuture;

            if (current != null)
                return current.cancel(mayInterruptIfRunning);

            if (_userFutureUpdater.compareAndSet(this, null, mayInterruptIfRunning ? CANCELLED_INTERRUPT : CANCELLED))
                return false;
        }
    }
}