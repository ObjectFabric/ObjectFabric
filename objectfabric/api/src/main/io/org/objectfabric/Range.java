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

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * Groups objects by 256. Distributes ids to transactional objects and keep a weak
 * reference to their shared version so they can be retrieved by id. TODO simplify.
 */
@SuppressWarnings("serial")
final class Range extends AtomicReferenceArray<Object> {

    static final class Ref extends WeakReference<TObject> {

        Ref(TObject object) {
            super(object);
        }
    }

    static final int LENGTH = 256;

    private final byte[] _uid;

    /*
     * TODO Compact/reuse ids.
     */

    Range(Workspace workspace, byte[] uid) {
        super(LENGTH);

        if (workspace == null || uid == null)
            throw new IllegalArgumentException();

        _uid = uid;
    }

    final byte[] uid() {
        return _uid;
    }

    @SuppressWarnings({ "rawtypes", "unchecked", "null" })
    final TObject getOrCreateTObject(Resource resource, int id, ObjectModel model, int classId, TType[] genericParameters) {
        TObject object;
        TObject newObject = null;

        for (;;) {
            Object value = get(id);
            object = null;

            if (value instanceof Ref) {
                object = ((Ref) value).get();

                if (object != null && object.resource() == resource)
                    break;
            }

            if (value instanceof PlatformConcurrentMap) {
                PlatformConcurrentMap map = (PlatformConcurrentMap) value;
                object = (TObject) map.get(resource);

                if (object != null)
                    break;
            }

            if (newObject == null) {
                newObject = model.createInstance(resource, classId, genericParameters);
                newObject.range(this);
                newObject.id(id);
                newObject.setReferencedByURI();
            }

            if (value == null || value instanceof Ref) {
                Object update;

                if (object == null)
                    update = new Ref(newObject);
                else {
                    /*
                     * Should never get there, only for security purposes. If a corrupt or
                     * crafted message tries to access an object from outside current
                     * resource, create a separate instance. Can't just close connection
                     * as this request might be the legitimate one.
                     */
                    PlatformConcurrentMap map = new PlatformConcurrentMap();
                    map.put(object.resource(), object);
                    map.put(resource, newObject);
                    update = map;
                }

                if (compareAndSet(id & 0xff, value, update)) {
                    object = newObject;
                    break;
                }
            } else {
                PlatformConcurrentMap map = (PlatformConcurrentMap) value;
                Object previous = map.putIfAbsent(resource, newObject);

                if (previous != null)
                    object = (TObject) previous;
                else
                    object = newObject;

                break;
            }
        }

        if (Debug.ENABLED) {
            Debug.assertion(object.resource() == resource);
            Debug.assertion(object.classId_() == classId);
            Debug.assertion(object.range() == this);
            Debug.assertion(object.id() == id);

            if (classId >= 0) {
                if (Platform.get().value() != Platform.GWT)
                    Debug.assertion(Platform.get().isInstance(model.getClass(classId, genericParameters), object));
            } else {
                Debug.assertion(model == Platform.get().defaultObjectModel());
                Helper.instance().disableEqualsOrHashCheck();
                Debug.assertion(Platform.get().defaultToString(object).contains("Array"));
                Helper.instance().enableEqualsOrHashCheck();
            }
        }

        return object;
    }
}
