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

public abstract class PlatformRef<T> extends cli.System.WeakReference {

    @SuppressWarnings("unused")
    public PlatformRef(T t, Object queue) {
        super(t);
    }

    @SuppressWarnings("unchecked")
    public T get() {
        return (T) get_Target();
    }

    abstract void collected();

    final void clear() {
    }
}