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

import of4gwt.misc.PlatformAdapter;

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                        
//                                                                              
//==============================================================================

/**
 * Sparse array to avoid allocating a new array the same length as the TArray each time an
 * update is done to it.
 */
class TArrayVersionTObject extends TIndexedNVersion {

    private TObject[][] _values;

    TType[] _genericParameters = new TType[] { TObject.TYPE };

    private static final boolean CAN_BE_TOBJECT = true;

    public TArrayVersionTObject(TObject.Version shared, int length) {
        super(shared, length);

        if (shared == null) {
            // Preallocate (C.f. TIndexedNVersion._writes)
            int arrayLength = getBits().length;
            _values = new TObject[arrayLength][];
        }
    }

    public final TObject[][] getValues() {
        return _values;
    }

    public final void setValues(TObject[][] value) {
        _values = value;
    }

    @Override
    public final TType[] getGenericParameters() {
        return _genericParameters;
    }

    public final TObject get(int index) {
        if (_values != null) {
            int foldedIndex = of4gwt.misc.Bits.getFoldedIntIndexFromIndex(getBits(), index);

            if (foldedIndex >= 0) {
                TObject[] current = _values[foldedIndex];

                if (current != null)
                    return current[index & of4gwt.misc.Bits.BIT_INDEX_MASK];
            }
        }

        return null;
    }

    @Override
    public final Object getAsObject(int index) {
        return get(index);
    }

    public final void set(int index, TObject value) {
        if (_values == null) {
            if (value != null) {
                int arrayLength = getBits().length;
                _values = new TObject[arrayLength][];
            }
        } else {
            if (of4gwt.misc.Debug.ENABLED)
                of4gwt.misc.Debug.assertion(_values.length == getBits().length);
        }

        if (_values != null) {
            int folded = of4gwt.misc.Bits.getFoldedIntIndexFromIndex(getBits(), index);

            if (_values[folded] == null) {
                if (value != null) {
                    int arrayLength = of4gwt.misc.Bits.BITS_PER_UNIT;
                    _values[folded] = new TObject[arrayLength];
                }
            }

            if (_values[folded] != null)
                _values[folded][index & of4gwt.misc.Bits.BIT_INDEX_MASK] = value;
        }
    }

    @SuppressWarnings("cast")
    @Override
    public final void setAsObject(int index, Object value) {
        set(index, (TObject) value);
    }

    @Override
    public final void reindexed(of4gwt.misc.Bits.Entry[] old) {
        if (_values != null) {
            TObject[][] oldValues = _values;
            int arrayLength = getBits().length;
            _values = new TObject[arrayLength][];

            for (int i = old.length - 1; i >= 0; i--) {
                if (old[i] != null) {
                    int intIndex = old[i].IntIndex;
                    int folded = of4gwt.misc.Bits.getFoldedIntIndexFromIntIndex(getBits(), intIndex);

                    if (of4gwt.misc.Debug.ENABLED)
                        of4gwt.misc.Debug.assertion(_values[folded] == null);

                    _values[folded] = oldValues[i];
                }
            }
        }
    }

    @Override
    public TObject.Version merge(TObject.Version target, TObject.Version next, int flags) {
        TArrayVersionTObject source = (TArrayVersionTObject) next;
        TArrayVersionTObject merged = (TArrayVersionTObject) super.merge(target, next, flags);
        merged.merge(source, flags);
        return merged;
    }

    @SuppressWarnings("cast")
    private final void merge(TArrayVersionTObject source, int flags) {
        boolean skip1 = !(CAN_BE_TOBJECT && isShared()) && _values == null;

        if (skip1)
            if (getBits() != null && source.getBits() != null)
                if (getBits().length != source.getBits().length)
                    skip1 = false;

        if (skip1) {
            if (source._values != null) {
                if ((flags & MERGE_FLAG_COPY_ARRAYS) != 0) {
                    int arrayLength = source._values.length;
                    _values = new TObject[arrayLength][];
                    PlatformAdapter.arraycopy(source._values, 0, _values, 0, _values.length);
                } else
                    _values = source._values;
            }
        } else {
            of4gwt.misc.Bits.Entry[] writes = source.getBits();

            if (writes != null) {
                for (int i = writes.length - 1; i >= 0; i--) {
                    if (writes[i] != null && writes[i].Value != 0) {
                        if (_values == null) {
                            int arrayLength = getBits().length;
                            _values = new TObject[arrayLength][];
                        }

                        int folded = of4gwt.misc.Bits.getFoldedIntIndexFromIntIndex(getBits(), writes[i].IntIndex);

                        if (CAN_BE_TOBJECT && isShared()) {
                            if (_values[folded] == null) {
                                int arrayLength = of4gwt.misc.Bits.BITS_PER_UNIT;
                                _values[folded] = new TObject[arrayLength];
                            }

                            Object[] m = (Object[]) (Object) _values[folded];
                            Object[] s = (Object[]) (Object) (source._values != null ? source._values[i] : null);
                            mergeObjects(m, writes[i], s);
                        } else {
                            boolean skip2 = false;

                            if ((flags & MERGE_FLAG_COPY_ARRAY_ELEMENTS) == 0) {
                                skip2 = _values[folded] == null;

                                if (!skip2) // All overwritten
                                    skip2 = writes[i].Value == -1 && source._values != null && source._values[i] != null;
                            } else if (_values[folded] == null) {
                                int arrayLength = of4gwt.misc.Bits.BITS_PER_UNIT;
                                _values[folded] = new TObject[arrayLength];
                            }

                            if (skip2)
                                _values[folded] = source._values != null ? source._values[i] : null;
                            else
                                merge(_values[folded], writes[i], source._values != null ? source._values[i] : null);
                        }
                    }
                }
            }
        }

        if (of4gwt.misc.Debug.ENABLED)
            checkInvariants();
    }

    private final void merge(TObject[] merged, of4gwt.misc.Bits.Entry writes, TObject[] source) {
        for (int i = of4gwt.misc.Bits.BITS_PER_UNIT - 1; i >= 0; i--)
            if (of4gwt.misc.Bits.get(writes.Value, i))
                merged[i] = source != null ? source[i] : null;
    }

    //

    @Override
    public final void writeWrite(Writer writer, int index) {
        if (writer.interrupted())
            writer.resume();

        writer.writeTObject(get(index));

        if (writer.interrupted()) {
            writer.interrupt(null);
            return;
        }
    }

    @Override
    public final void readWrite(Reader reader, int index) {
        if (reader.interrupted())
            reader.resume();

        TObject object = reader.readTObject();

        if (reader.interrupted()) {
            reader.interrupt(null);
            return;
        }

        set(index, object);
    }

    //

    @Override
    public TObject.Version createVersion() {
        return new TArrayVersionTObject(this, length());
    }

    @Override
    public int getClassId() {
        if (of4gwt.misc.Debug.ENABLED)
            of4gwt.misc.Debug.assertion(length() >= 0);

        return -length() - 1;
    }

    //

    @SuppressWarnings("cast")
    @Override
    public void checkInvariants_() {
        super.checkInvariants_();

        if (getValues() != null) {
            of4gwt.misc.Debug.assertion(getValues().length == getBits().length);

            for (int i = 0; i < getValues().length; i++) {
                if (getValues()[i] != null) {
                    for (int t = 0; t < getValues()[i].length; t++) {
                        TObject value = getValues()[i][t];

                        if (value != null) {
                            of4gwt.misc.Debug.assertion(of4gwt.misc.Bits.get(getBits()[i].Value, t));

                            if (isShared())
                                of4gwt.misc.Debug.assertion(!(((Object) value) instanceof UserTObject));
                            else
                                of4gwt.misc.Debug.assertion(!(((Object) value) instanceof TObject.Version));
                        }
                    }
                }
            }
        }
    }
}
// End (for .NET)
