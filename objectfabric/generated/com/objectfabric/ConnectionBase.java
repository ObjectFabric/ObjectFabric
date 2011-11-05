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

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                
//                                                                              
//==============================================================================

@SuppressWarnings({ "hiding", "unchecked", "static-access", "unused" })
abstract class ConnectionBase extends com.objectfabric.TGeneratedFields32 {

    protected ConnectionBase(com.objectfabric.Site target) {
        this(com.objectfabric.Transaction.getDefaultTrunk(), target);
    }

    protected ConnectionBase(com.objectfabric.Transaction trunk, com.objectfabric.Site target) {
        this(new Version(null, FIELD_COUNT), trunk, target);
    }

    protected ConnectionBase(com.objectfabric.TObject.Version shared, com.objectfabric.Transaction trunk, com.objectfabric.Site target) {
        super(shared, trunk);

        ((Version) shared)._target = shared.mergeTObject(((Version) shared)._target, target);

        if (target != null)
            ((Version) shared).setBit(TARGET_INDEX);
    }

    public static final com.objectfabric.TType TYPE = new com.objectfabric.TType(com.objectfabric.DefaultObjectModel.getInstance(), com.objectfabric.DefaultObjectModelBase.COM_OBJECTFABRIC_CONNECTION_CLASS_ID);

    public final com.objectfabric.Site getTarget() {
        Version v = (Version) getSharedVersion_objectfabric();
        return (com.objectfabric.Site) getUserTObject_objectfabric(v._target);
    }

    public static final int TARGET_INDEX = 0;

    public static final java.lang.String TARGET_NAME = "target";

    public static final com.objectfabric.TType TARGET_TYPE = com.objectfabric.Site.TYPE;

    public static final int FIELD_COUNT = 1;

    @Override
    public int getFieldCount() {
        return FIELD_COUNT;
    }

    @Override
    public java.lang.String getFieldName(int index) {
        return getFieldNameStatic(index);
    }

    @SuppressWarnings("static-access")
    public static java.lang.String getFieldNameStatic(int index) {
        switch (index) {
            case TARGET_INDEX:
                return TARGET_NAME;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public  com.objectfabric.TType getFieldType(int index) {
        return getFieldTypeStatic(index);
    }

    @SuppressWarnings("static-access")
    public static com.objectfabric.TType getFieldTypeStatic(int index) {
        switch (index) {
            case TARGET_INDEX:
                return TARGET_TYPE;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected static class Version extends com.objectfabric.TGeneratedFields32.Version {

        public com.objectfabric.TObject _target;

        private static final int _readOnlys;

        static {
            int readOnlys = 0;
            readOnlys = setBit(readOnlys, TARGET_INDEX);
            _readOnlys = readOnlys;
        }

        public Version(com.objectfabric.TGeneratedFields32.Version shared, int length) {
            super(shared, length);
        }

        @Override
        public java.lang.Object getAsObject(int index) {
            switch (index) {
                case TARGET_INDEX:
                    return getUserTObject_objectfabric(_target);
                default:
                    return super.getAsObject(index);
            }
        }

        @Override
        public void setAsObject(int index, java.lang.Object value) {
            switch (index) {
                case TARGET_INDEX:
                    _target = (com.objectfabric.Site) value;
                    break;
                default:
                    super.setAsObject(index, value);
                    break;
            }
        }

        @Override
        public com.objectfabric.TObject.Version merge(com.objectfabric.TObject.Version target, com.objectfabric.TObject.Version next, int flags) {
            ConnectionBase.Version source = (ConnectionBase.Version) next;
            ConnectionBase.Version merged = (ConnectionBase.Version) super.merge(target, next, flags);

            if (source.hasBits()) {
                if (source.getBit(TARGET_INDEX))
                    merged._target = mergeTObject(merged._target, source._target);
            }

            return merged;
        }

        @Override
        public int getReadOnlys() {
            return _readOnlys;
        }

        @Override
        public void writeWrite(com.objectfabric.Writer writer, int index) {
            if (writer.interrupted())
                writer.resume();

            switch (index) {
                case TARGET_INDEX: {
                    writer.writeTObject(_target);

                    if (writer.interrupted()) {
                        writer.interrupt(null);
                        return;
                    }
                    break;
                }
                default: {
                    super.writeWrite(writer, index);

                    if (writer.interrupted()) {
                        writer.interrupt(null);
                        return;
                    }
                    break;
                }
            }
        }

        @Override
        public void readWrite(com.objectfabric.Reader reader, int index) {
            if (reader.interrupted())
                reader.resume();

            switch (index) {
                case TARGET_INDEX: {
                    ConnectionBase.Version shared = (ConnectionBase.Version) getUnion();
                    shared._target = getSharedVersion_objectfabric(reader.readTObject());

                    if (reader.interrupted()) {
                        reader.interrupt(null);
                        return;
                    }

                    shared.setBit(TARGET_INDEX);
                    unsetBit(TARGET_INDEX);
                    break;
                }
                default: {
                    super.readWrite(reader, index);

                    if (reader.interrupted()) {
                        reader.interrupt(null);
                        return;
                    }
                    break;
                }
            }
        }

        @Override
        public com.objectfabric.TObject.Version createVersion() {
            return new ConnectionBase.Version(this, FIELD_COUNT);
        }

        @Override
        public int getClassId() {
            return com.objectfabric.DefaultObjectModelBase.COM_OBJECTFABRIC_CONNECTION_CLASS_ID;
        }

        @SuppressWarnings("static-access")
        @Override
        public com.objectfabric.ObjectModel getObjectModel() {
            return com.objectfabric.DefaultObjectModel.getInstance();
        }
    }
}
