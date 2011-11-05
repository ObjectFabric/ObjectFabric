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

package of4gwt;

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                
//                                                                              
//==============================================================================

@SuppressWarnings({ "hiding", "unchecked", "static-access", "unused" })
abstract class ConnectionBase extends of4gwt.TGeneratedFields32 {

    protected ConnectionBase(of4gwt.Site target) {
        this(of4gwt.Transaction.getDefaultTrunk(), target);
    }

    protected ConnectionBase(of4gwt.Transaction trunk, of4gwt.Site target) {
        this(new Version(null, FIELD_COUNT), trunk, target);
    }

    protected ConnectionBase(of4gwt.TObject.Version shared, of4gwt.Transaction trunk, of4gwt.Site target) {
        super(shared, trunk);

        ((Version) shared)._target = shared.mergeTObject(((Version) shared)._target, target);

        if (target != null)
            ((Version) shared).setBit(TARGET_INDEX);
    }

    public static final of4gwt.TType TYPE = new of4gwt.TType(of4gwt.DefaultObjectModel.getInstance(), of4gwt.DefaultObjectModelBase.COM_OBJECTFABRIC_CONNECTION_CLASS_ID);

    public final of4gwt.Site getTarget() {
        Version v = (Version) getSharedVersion_objectfabric();
        return (of4gwt.Site) getUserTObject_objectfabric(v._target);
    }

    public static final int TARGET_INDEX = 0;

    public static final java.lang.String TARGET_NAME = "target";

    public static final of4gwt.TType TARGET_TYPE = of4gwt.Site.TYPE;

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
    public  of4gwt.TType getFieldType(int index) {
        return getFieldTypeStatic(index);
    }

    @SuppressWarnings("static-access")
    public static of4gwt.TType getFieldTypeStatic(int index) {
        switch (index) {
            case TARGET_INDEX:
                return TARGET_TYPE;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected static class Version extends of4gwt.TGeneratedFields32.Version {

        public of4gwt.TObject _target;

        private static final int _readOnlys;

        static {
            int readOnlys = 0;
            readOnlys = setBit(readOnlys, TARGET_INDEX);
            _readOnlys = readOnlys;
        }

        public Version(of4gwt.TGeneratedFields32.Version shared, int length) {
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
                    _target = (of4gwt.Site) value;
                    break;
                default:
                    super.setAsObject(index, value);
                    break;
            }
        }

        @Override
        public of4gwt.TObject.Version merge(of4gwt.TObject.Version target, of4gwt.TObject.Version next, int flags) {
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
        public void writeWrite(of4gwt.Writer writer, int index) {
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
        public void readWrite(of4gwt.Reader reader, int index) {
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
        public of4gwt.TObject.Version createVersion() {
            return new ConnectionBase.Version(this, FIELD_COUNT);
        }

        @Override
        public int getClassId() {
            return of4gwt.DefaultObjectModelBase.COM_OBJECTFABRIC_CONNECTION_CLASS_ID;
        }

        @SuppressWarnings("static-access")
        @Override
        public of4gwt.ObjectModel getObjectModel() {
            return of4gwt.DefaultObjectModel.getInstance();
        }
    }
}
