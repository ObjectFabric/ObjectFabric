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

package org.objectfabric;

abstract class PlatformThreadContext {

    private static final ThreadLocal<Object> _instance = new ThreadLocal<Object>();

    static Object getInstance() {
        return _instance.get();
    }

    static void setInstance(Object value) {
        _instance.set(value);
    }
}
