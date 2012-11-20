/**
 * This file is part of ObjectFabric (http://objectfabric.org).
 *
 * ObjectFabric is licensed under the Apache License, Version 2.0, the terms
 * of which may be found at http://www.apache.org/licenses/LICENSE-2.0.html.
 * 
 * Copyright ObjectFabric Inc.
 * 
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 * WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package org.objectfabric.generated;

import org.objectfabric.Closure;
import org.objectfabric.IndexListener;
import org.objectfabric.Internal;
import org.objectfabric.Resource;
import org.objectfabric.TArrayTObject;
import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.Exportable;

//==============================================================================
//
//THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                        
//
//==============================================================================

@SuppressWarnings("unchecked")
@Export("TArrayTObject")
@ExportPackage("of")
public class JSArrayTObject implements Exportable {

    // TODO back with JS typed arrays?
    public static final class ArrayInternal extends TArrayTObject implements Internal {

        JSArrayTObject _js;

        public ArrayInternal(Resource resource, int length) {
            super(resource, length);
        }

        @Override
        public Exportable getOrCreateJS() {
            if (_js == null)
                _js = new JSArrayTObject(this);

            return _js;
        }
    }

    private final ArrayInternal _internal;

    JSArrayTObject(ArrayInternal internal) {
        _internal = internal;
    }

    public org.objectfabric.TObject get(int index) {
        return _internal.get(index);
    }

    public int length() {
        return _internal.length();
    }

    public void set(int index, org.objectfabric.TObject value) {
        _internal.set(index, value);
    }

    public void onset(final Closure closure) {
        _internal.addListener(new IndexListener() {

            @Override
            public void onSet(int index) {
                closure.runPrimitive(index);
            }
        });
    }
}
