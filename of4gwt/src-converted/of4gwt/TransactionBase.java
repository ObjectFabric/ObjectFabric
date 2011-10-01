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

@SuppressWarnings({ "hiding", "unchecked", "static-access" })
abstract class TransactionBase extends of4gwt.TGeneratedFields32 {

    protected TransactionBase(of4gwt.Transaction parentImpl, int type, of4gwt.Transaction.ConflictDetection conflictDetection, of4gwt.Transaction.Consistency consistency, of4gwt.Transaction.Granularity granularity) {
        this(of4gwt.Transaction.getDefaultTrunk(), parentImpl, type, conflictDetection, consistency, granularity);
    }

    protected TransactionBase(of4gwt.Transaction trunk, of4gwt.Transaction parentImpl, int type, of4gwt.Transaction.ConflictDetection conflictDetection, of4gwt.Transaction.Consistency consistency, of4gwt.Transaction.Granularity granularity) {
        this(new Version(null, FIELD_COUNT), trunk, parentImpl, type, conflictDetection, consistency, granularity);
    }

    protected TransactionBase(of4gwt.TObject.Version shared, of4gwt.Transaction trunk, of4gwt.Transaction parentImpl, int type, of4gwt.Transaction.ConflictDetection conflictDetection, of4gwt.Transaction.Consistency consistency, of4gwt.Transaction.Granularity granularity) {
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

    protected final of4gwt.Transaction getParentImpl() {
        Version v = (Version) getSharedVersion_objectfabric();
        return (of4gwt.Transaction) getUserTObject_objectfabric(v._parentImpl);
    }

    protected final int getType() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._type;
    }

    public final of4gwt.Transaction.ConflictDetection getConflictDetection() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._conflictDetection;
    }

    public final of4gwt.Transaction.Consistency getConsistency() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._consistency;
    }

    public final of4gwt.Transaction.Granularity getGranularity() {
        Version v = (Version) getSharedVersion_objectfabric();
        return v._granularity;
    }

    protected static final int PARENT_IMPL_INDEX = 0;

    protected static final java.lang.String PARENT_IMPL_NAME = "parentImpl";

    protected static final java.lang.Class PARENT_IMPL_CLASS = of4gwt.Transaction.class;

    protected static final int TYPE_INDEX = 1;

    protected static final java.lang.String TYPE_NAME = "type";

    protected static final java.lang.Class TYPE_CLASS = int.class;

    public static final int CONFLICT_DETECTION_INDEX = 2;

    public static final java.lang.String CONFLICT_DETECTION_NAME = "conflictDetection";

    public static final java.lang.Class CONFLICT_DETECTION_CLASS = of4gwt.Transaction.ConflictDetection.class;

    public static final int CONSISTENCY_INDEX = 3;

    public static final java.lang.String CONSISTENCY_NAME = "consistency";

    public static final java.lang.Class CONSISTENCY_CLASS = of4gwt.Transaction.Consistency.class;

    public static final int GRANULARITY_INDEX = 4;

    public static final java.lang.String GRANULARITY_NAME = "granularity";

    public static final java.lang.Class GRANULARITY_CLASS = of4gwt.Transaction.Granularity.class;

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
    public java.lang.Class getFieldClass(int index) {
        return getFieldClassStatic(index);
    }

    @SuppressWarnings("static-access")
    public static java.lang.Class getFieldClassStatic(int index) {
        switch (index) {
            case PARENT_IMPL_INDEX:
                return PARENT_IMPL_CLASS;
            case TYPE_INDEX:
                return TYPE_CLASS;
            case CONFLICT_DETECTION_INDEX:
                return CONFLICT_DETECTION_CLASS;
            case CONSISTENCY_INDEX:
                return CONSISTENCY_CLASS;
            case GRANULARITY_INDEX:
                return GRANULARITY_CLASS;
            default:
                throw new IllegalArgumentException();
        }
    }

    protected static class Version extends of4gwt.TGeneratedFields32.Version {

        public of4gwt.TObject _parentImpl;

        public int _type;

        public of4gwt.Transaction.ConflictDetection _conflictDetection;

        public of4gwt.Transaction.Consistency _consistency;

        public of4gwt.Transaction.Granularity _granularity;

        private static final of4gwt.Transaction.Granularity[] COM_OBJECTFABRIC_TRANSACTION_GRANULARITY_ENUM_VALUES_ARRAY = of4gwt.Transaction.Granularity.values();

        private static final of4gwt.Transaction.ConflictDetection[] COM_OBJECTFABRIC_TRANSACTION_CONFLICTDETECTION_ENUM_VALUES_ARRAY = of4gwt.Transaction.ConflictDetection.values();

        private static final of4gwt.Transaction.Consistency[] COM_OBJECTFABRIC_TRANSACTION_CONSISTENCY_ENUM_VALUES_ARRAY = of4gwt.Transaction.Consistency.values();

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

        public Version(of4gwt.TGeneratedFields32.Version shared, int length) {
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
                    _parentImpl = (of4gwt.Transaction) value;
                    break;
                case TYPE_INDEX:
                    _type = ((java.lang.Integer) value).intValue();
                    break;
                case CONFLICT_DETECTION_INDEX:
                    _conflictDetection = (of4gwt.Transaction.ConflictDetection) value;
                    break;
                case CONSISTENCY_INDEX:
                    _consistency = (of4gwt.Transaction.Consistency) value;
                    break;
                case GRANULARITY_INDEX:
                    _granularity = (of4gwt.Transaction.Granularity) value;
                    break;
                default:
                    super.setAsObject(index, value);
                    break;
            }
        }

        @Override
        public of4gwt.TObject.Version merge(of4gwt.TObject.Version target, of4gwt.TObject.Version next, int flags) {
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
        public void writeWrite(of4gwt.Writer writer, int index) {
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
        public void readWrite(of4gwt.Reader reader, int index) {
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
        public of4gwt.TObject.Version createVersion() {
            return new TransactionBase.Version(this, FIELD_COUNT);
        }

        @Override
        public int getClassId() {
            return of4gwt.DefaultObjectModelBase.COM_OBJECTFABRIC_TRANSACTION_CLASS_ID;
        }

        @SuppressWarnings("static-access")
        @Override
        public of4gwt.ObjectModel getObjectModel() {
            return of4gwt.DefaultObjectModel.getInstance();
        }
    }
}
