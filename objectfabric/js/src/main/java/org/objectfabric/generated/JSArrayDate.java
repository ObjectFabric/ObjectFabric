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

import org.objectfabric.IndexListener;
import org.objectfabric.JS.Closure;
import org.objectfabric.JS.External;
import org.objectfabric.JS.Internal;
import org.objectfabric.JSResource;
import org.objectfabric.Resource;
import org.objectfabric.TArrayDate;
import org.timepedia.exporter.client.Export;
import org.timepedia.exporter.client.ExportPackage;
import org.timepedia.exporter.client.NoExport;

//==============================================================================
//
//THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                        
//
//==============================================================================

@SuppressWarnings("unchecked")
@Export("TArrayDate")
@ExportPackage("of")
public class JSArrayDate implements External {

    // TODO back with JS typed arrays?
    public static final class ArrayInternal extends TArrayDate implements Internal {

        JSArrayDate _js;

        public ArrayInternal(Resource resource, int length) {
            super(resource, length);
        }

        @Override
        public External external() {
            if (_js == null) {
                _js = new JSArrayDate();
                _js._internal = this;
            }

            return _js;
        }
    }

    private ArrayInternal _internal;

    public JSArrayDate(JSResource resource, int length) {
        _internal = new ArrayInternal((Resource) resource.internal(), length);
    }

    @NoExport
    public JSArrayDate() {
    }

    @Override
    public Internal internal() {
        return _internal;
    }

    public java.util.Date get(int index) {
        return _internal.get(index);
    }

    public int length() {
        return _internal.length();
    }

    public void set(int index, java.util.Date value) {
        _internal.set(index, value);
    }

    public void onset(final Closure closure) {
        _internal.addListener(new IndexListener() {

            @Override
            public void onSet(int index) {
                closure.runImmutable(index);
            }
        });
    }
}
