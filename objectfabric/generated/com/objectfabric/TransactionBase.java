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
abstract class TransactionBase extends com.objectfabric.TGeneratedFields32 {

    protected TransactionBase(com.objectfabric.Transaction parentImpl, int type, com.objectfabric.Transaction.ConflictDetection conflictDetection, com.objectfabric.Transaction.Consistency consistency, com.objectfabric.Transaction.Granularity granularity) {
        this(com.objectfabric.Transaction.getDefaultTrunk(), parentImpl, type, conflictDetection, consistency, granularity);
    }

    protected TransactionBase(com.objectfabric.Transaction trunk, com.objectfabric.Transaction parentImpl, int type, com.objectfabric.Transaction.ConflictDetection conflictDetection, com.objectfabric.Transaction.Consistency consistency, com.objectfabric.Transaction.Granularity granularity) {
        this(new Version(null, FIELD_COUNT), trunk, parentImpl, type, conflictDetection, consistency, granularity);
    }

    protected TransactionBase(com.objectfabric.TObject.Version shared, com.objectfabric.Transaction trunk, com.objectfabric.Transaction parentImpl, int type, com.objectfabric.Transaction.ConflictDetection conflictDetection, com.objectfabric.Transaction.Consistency consistency, com.objectfabric.Transaction.Granularity granularity) {
        super(shared, trunk);

        ((Version) shared)._parentImpl = shared.mergeTObject(((Version) shared)._parentImpl, parentImpl);

        if (parentImpl != null)
            ((Version) shared).setBit(PARENT_IMPL_INDEX);

        ((Version) shared)._type = type;

        if (type != 0)
            ((Version) shared).setBit(TYPE_INDEX);

        ((Version) shared)._conflictDetection = conflictDetection;

        if (conflictDetection != null)
            ((Version) shared).setBit(CONFLICT_DETECTION_INDEX);

        ((Version) shared)._consistency = consistency;

        if (consistency != null)
            ((Version) shared).setBit(CONSISTENCY_INDEX);

        ((Version) shared)._granularity = granularity;

        if (granularity != null)
            ((Version) shared).setBit(GRANULARITY_INDEX);
    }

    protected final com.objectfabric.Transaction getParentImpl() {
        Version v = (Version) getSharedVersion_objectfabric();
        return (com.objectfabric.Transaction) getUserTObject_objectfabric(v._parentImpl);
    }

    protected final int getType() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._type;
    }

    public final com.objectfabric.Transaction.ConflictDetection getConflictDetection() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._conflictDetection;
    }

    public final com.objectfabric.Transaction.Consistency getConsistency() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._consistency;
    }

    public final com.objectfabric.Transaction.Granularity getGranularity() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._granularity;
    }

    protected static final int PARENT_IMPL_INDEX = 0;

    protected static final java.lang.String PARENT_IMPL_NAME = "parentImpl";

    protected static final com.objectfabric.TType PARENT_IMPL_TYPE = com.objectfabric.Transaction.TYPE;

    protected static final int TYPE_INDEX = 1;

    protected static final java.lang.String TYPE_NAME = "type";

    protected static final com.objectfabric.TType TYPE_TYPE = com.objectfabric.ImmutableClass.INTEGER.getType();

    public static final int CONFLICT_DETECTION_INDEX = 2;

    public static final java.lang.String CONFLICT_DETECTION_NAME = "conflictDetection";

    public static final com.objectfabric.TType CONFLICT_DETECTION_TYPE = new com.objectfabric.TType(com.objectfabric.Transaction.ConflictDetection.class);

    public static final int CONSISTENCY_INDEX = 3;

    public static final java.lang.String CONSISTENCY_NAME = "consistency";

    public static final com.objectfabric.TType CONSISTENCY_TYPE = new com.objectfabric.TType(com.objectfabric.Transaction.Consistency.class);

    public static final int GRANULARITY_INDEX = 4;

    public static final java.lang.String GRANULARITY_NAME = "granularity";

    public static final com.objectfabric.TType GRANULARITY_TYPE = new com.objectfabric.TType(com.objectfabric.Transaction.Granularity.class);

    public static final int FIELD_COUNT = 5;

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
            case PARENT_IMPL_INDEX:
                return PARENT_IMPL_NAME;
            case TYPE_INDEX:
                return TYPE_NAME;
            case CONFLICT_DETECTION_INDEX:
                return CONFLICT_DETECTION_NAME;
            case CONSISTENCY_INDEX:
                return CONSISTENCY_NAME;
            case GRANULARITY_INDEX:
                return GRANULARITY_NAME;
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
            case PARENT_IMPL_INDEX:
                return PARENT_IMPL_TYPE;
            case TYPE_INDEX:
                return TYPE_TYPE;
            case CONFLICT_DETECTION_INDEX:
                return CONFLICT_DETECTION_TYPE;
            case CONSISTENCY_INDEX:
                return CONSISTENCY_TYPE;
            case GRANULARITY_INDEX:
                return GRANULARITY_TYPE;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected static class Version extends com.objectfabric.TGeneratedFields32.Version {

        public com.objectfabric.TObject _parentImpl;

        public int _type;

        public com.objectfabric.Transaction.ConflictDetection _conflictDetection;

        public com.objectfabric.Transaction.Consistency _consistency;

        public com.objectfabric.Transaction.Granularity _granularity;

        private static final com.objectfabric.Transaction.ConflictDetection[] COM_OBJECTFABRIC_TRANSACTION_CONFLICTDETECTION_ENUM_VALUES_ARRAY = com.objectfabric.Transaction.ConflictDetection.values();

        private static final com.objectfabric.Transaction.Consistency[] COM_OBJECTFABRIC_TRANSACTION_CONSISTENCY_ENUM_VALUES_ARRAY = com.objectfabric.Transaction.Consistency.values();

        private static final com.objectfabric.Transaction.Granularity[] COM_OBJECTFABRIC_TRANSACTION_GRANULARITY_ENUM_VALUES_ARRAY = com.objectfabric.Transaction.Granularity.values();

        private static final int _readOnlys;

        static {
            int readOnlys = 0;
            readOnlys = setBit(readOnlys, PARENT_IMPL_INDEX);
            readOnlys = setBit(readOnlys, TYPE_INDEX);
            readOnlys = setBit(readOnlys, CONFLICT_DETECTION_INDEX);
            readOnlys = setBit(readOnlys, CONSISTENCY_INDEX);
            readOnlys = setBit(readOnlys, GRANULARITY_INDEX);
            _readOnlys = readOnlys;
        }

        public Version(com.objectfabric.TGeneratedFields32.Version shared, int length) {
            super(shared, length);
        }

        @Override
        public java.lang.Object getAsObject(int index) {
            switch (index) {
                case PARENT_IMPL_INDEX:
                    return getUserTObject_objectfabric(_parentImpl);
                case TYPE_INDEX:
                    return _type;
                case CONFLICT_DETECTION_INDEX:
                    return _conflictDetection;
                case CONSISTENCY_INDEX:
                    return _consistency;
                case GRANULARITY_INDEX:
                    return _granularity;
                default:
                    return super.getAsObject(index);
            }
        }

        @Override
        public void setAsObject(int index, java.lang.Object value) {
            switch (index) {
                case PARENT_IMPL_INDEX:
                    _parentImpl = (com.objectfabric.Transaction) value;
                    break;
                case TYPE_INDEX:
                    _type = ((java.lang.Integer) value).intValue();
                    break;
                case CONFLICT_DETECTION_INDEX:
                    _conflictDetection = (com.objectfabric.Transaction.ConflictDetection) value;
                    break;
                case CONSISTENCY_INDEX:
                    _consistency = (com.objectfabric.Transaction.Consistency) value;
                    break;
                case GRANULARITY_INDEX:
                    _granularity = (com.objectfabric.Transaction.Granularity) value;
                    break;
                default:
                    super.setAsObject(index, value);
                    break;
            }
        }

        @Override
        public com.objectfabric.TObject.Version merge(com.objectfabric.TObject.Version target, com.objectfabric.TObject.Version next, int flags) {
            TransactionBase.Version source = (TransactionBase.Version) next;
            TransactionBase.Version merged = (TransactionBase.Version) super.merge(target, next, flags);

            if (source.hasBits()) {
                if (source.getBit(PARENT_IMPL_INDEX))
                    merged._parentImpl = mergeTObject(merged._parentImpl, source._parentImpl);

                if (source.getBit(TYPE_INDEX))
                    merged._type = source._type;

                if (source.getBit(CONFLICT_DETECTION_INDEX))
                    merged._conflictDetection = source._conflictDetection;

                if (source.getBit(CONSISTENCY_INDEX))
                    merged._consistency = source._consistency;

                if (source.getBit(GRANULARITY_INDEX))
                    merged._granularity = source._granularity;
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
                case PARENT_IMPL_INDEX: {
                    writer.writeTObject(_parentImpl);

                    if (writer.interrupted()) {
                        writer.interrupt(null);
                        return;
                    }
                    break;
                }
                case TYPE_INDEX: {
                    if (!writer.canWriteInteger()) {
                        writer.interrupt(null);
                        return;
                    }

                    writer.writeInteger(_type);
                    break;
                }
                case CONFLICT_DETECTION_INDEX: {
                    if (!writer.canWriteInteger()) {
                        writer.interrupt(null);
                        return;
                    }

                    writer.writeInteger(_conflictDetection.ordinal());
                    break;
                }
                case CONSISTENCY_INDEX: {
                    if (!writer.canWriteInteger()) {
                        writer.interrupt(null);
                        return;
                    }

                    writer.writeInteger(_consistency.ordinal());
                    break;
                }
                case GRANULARITY_INDEX: {
                    if (!writer.canWriteInteger()) {
                        writer.interrupt(null);
                        return;
                    }

                    writer.writeInteger(_granularity.ordinal());
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
                case PARENT_IMPL_INDEX: {
                    TransactionBase.Version shared = (TransactionBase.Version) getUnion();
                    shared._parentImpl = getSharedVersion_objectfabric(reader.readTObject());

                    if (reader.interrupted()) {
                        reader.interrupt(null);
                        return;
                    }

                    shared.setBit(PARENT_IMPL_INDEX);
                    unsetBit(PARENT_IMPL_INDEX);
                    break;
                }
                case TYPE_INDEX: {
                    if (!reader.canReadInteger()) {
                        reader.interrupt(null);
                        return;
                    }

                    TransactionBase.Version shared = (TransactionBase.Version) getUnion();
                    shared._type = reader.readInteger();

                    shared.setBit(TYPE_INDEX);
                    unsetBit(TYPE_INDEX);
                    break;
                }
                case CONFLICT_DETECTION_INDEX: {
                    if (!reader.canReadInteger()) {
                        reader.interrupt(null);
                        return;
                    }

                    TransactionBase.Version shared = (TransactionBase.Version) getUnion();
                    shared._conflictDetection = COM_OBJECTFABRIC_TRANSACTION_CONFLICTDETECTION_ENUM_VALUES_ARRAY[reader.readInteger()];

                    shared.setBit(CONFLICT_DETECTION_INDEX);
                    unsetBit(CONFLICT_DETECTION_INDEX);
                    break;
                }
                case CONSISTENCY_INDEX: {
                    if (!reader.canReadInteger()) {
                        reader.interrupt(null);
                        return;
                    }

                    TransactionBase.Version shared = (TransactionBase.Version) getUnion();
                    shared._consistency = COM_OBJECTFABRIC_TRANSACTION_CONSISTENCY_ENUM_VALUES_ARRAY[reader.readInteger()];

                    shared.setBit(CONSISTENCY_INDEX);
                    unsetBit(CONSISTENCY_INDEX);
                    break;
                }
                case GRANULARITY_INDEX: {
                    if (!reader.canReadInteger()) {
                        reader.interrupt(null);
                        return;
                    }

                    TransactionBase.Version shared = (TransactionBase.Version) getUnion();
                    shared._granularity = COM_OBJECTFABRIC_TRANSACTION_GRANULARITY_ENUM_VALUES_ARRAY[reader.readInteger()];

                    shared.setBit(GRANULARITY_INDEX);
                    unsetBit(GRANULARITY_INDEX);
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
            return new TransactionBase.Version(this, FIELD_COUNT);
        }

        @Override
        public int getClassId() {
            return com.objectfabric.DefaultObjectModelBase.COM_OBJECTFABRIC_TRANSACTION_CLASS_ID;
        }

        @SuppressWarnings("static-access")
        @Override
        public com.objectfabric.ObjectModel getObjectModel() {
            return com.objectfabric.DefaultObjectModel.getInstance();
        }
    }
}
