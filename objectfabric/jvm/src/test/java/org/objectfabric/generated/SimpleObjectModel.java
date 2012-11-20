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

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                
//                                                                              
//==============================================================================

@SuppressWarnings({ "hiding", "unchecked", "static-access", "rawtypes" })
public final class SimpleObjectModel extends org.objectfabric.ObjectModel {

    private static final byte[] UID = { -76, -83, 1, -128, 12, -128, 127, -18, 8, -50, -23, 98, 11, -42, -14, 94 };

    // volatile not needed, models have no state
    private static SimpleObjectModel _instance;

    private static final java.lang.Object _lock = new java.lang.Object();

    protected SimpleObjectModel() {
    }

    public static SimpleObjectModel instance() {
        if (_instance == null) {
            synchronized (_lock) {
                if (_instance == null)
                    _instance = new SimpleObjectModel();
            }
        }

        return _instance;
    }

    public static byte[] uid() {
        byte[] copy = new byte[UID.length];
        arraycopy(UID, copy);
        return copy;
    }

    @Override
    protected byte[] uid_() {
        return UID;
    }

    /**
     * Registers this object model so that its classes can be serialized by the
     * system.
     */
    public static void register() {
        register(instance());
    }

    @Override
    protected java.lang.String objectFabricVersion() {
        return "0.9";
    }

    public static final int CLASS_COUNT = 1;

    public static final int ORG_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID = 0;

    public static final int METHOD_COUNT = 0;

    @Override
    protected java.lang.Class getClass(int classId, org.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case ORG_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID:
                return org.objectfabric.generated.SimpleClass.class;
        }

        return super.getClass(classId, genericParameters);
    }

    @Override
    protected org.objectfabric.TObject createInstance(org.objectfabric.Resource resource, int classId, org.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case ORG_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID:
                return new org.objectfabric.generated.SimpleClass(resource);
        }

        return super.createInstance(resource, classId, genericParameters);
    }

}
