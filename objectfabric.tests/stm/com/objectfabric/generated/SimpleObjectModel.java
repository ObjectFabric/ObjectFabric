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

package com.objectfabric.generated;

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                
//                                                                              
//==============================================================================

@SuppressWarnings({ "hiding", "unchecked", "static-access" })
public class SimpleObjectModel extends com.objectfabric.ObjectModel {

    private static final byte[] UID = { 1, -108, 3, 101, -126, -53, 10, 9, -103, -19, -102, -26, -84, 76, -66, 106 };

    private static volatile SimpleObjectModel _instance;

    private static final Object _lock = new Object();

    protected SimpleObjectModel(com.objectfabric.TObject.Version shared) {
        super(shared);
    }

    protected SimpleObjectModel() {
        this(new Version(null));
    }

    public static SimpleObjectModel getInstance() {
        if (_instance == null) {
            synchronized (_lock) {
                if (_instance == null)
                    _instance = new SimpleObjectModel();
            }
        }

        return _instance;
    }

    public static byte[] getUID() {
        byte[] copy = new byte[UID.length];
        com.objectfabric.misc.PlatformAdapter.arraycopy(UID, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     * Registers this object model so that its classes can be serialized by the
     * system.
     */
    public static void register() {
        register(getInstance());
    }

    /**
     * Registers an object model which can override <code>createInstance</code>
     * to let the system use your own derived classes. This is necessary e.g. to
     * implement remote methods on transactional objects.
     */
    public static void registerOverridenModel(SimpleObjectModel model) {
        synchronized (_lock) {
            if (_instance != null)
                throw new RuntimeException("Object model has already been registered. This method can only be called at program startup.");

            _instance = model;
        }

        register(model);
    }

    @Override
    protected java.lang.String getObjectFabricVersion() {
        return "0.8";
    }

    public static final int CLASS_COUNT = 1;

    public static final int COM_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID = 0;

    public static final int METHOD_COUNT = 0;

    @Override
    protected java.lang.Class getClass(int classId, com.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case COM_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID:
                return com.objectfabric.generated.SimpleClass.class;
        }

        return super.getClass(classId, genericParameters);
    }

    @Override
    protected com.objectfabric.TObject.UserTObject createInstance(com.objectfabric.Transaction trunk, int classId, com.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case COM_OBJECTFABRIC_GENERATED_SIMPLECLASS_CLASS_ID:
                return new com.objectfabric.generated.SimpleClass(trunk);
        }

        return super.createInstance(trunk, classId, genericParameters);
    }

    protected static final class Version extends com.objectfabric.ObjectModel.Version {

        public Version(com.objectfabric.ObjectModel.Version shared) {
            super(shared);
        }

        @Override
        public byte[] getUID() {
            return SimpleObjectModel.UID;
        }
    }
}
