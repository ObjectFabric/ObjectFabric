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

import com.objectfabric.misc.PlatformAdapter;

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                        
//                                                                              
//==============================================================================

/**
 * Sparse array to avoid allocating a new array the same length as the TArray each time an
 * update is done to it.
 */
class TArrayVersionLong extends TIndexedNVersion {

    private long[][] _values;

    private static final TType[] _genericParameters = new TType[] { new TType(ImmutableClass.LONG) };

    private static final boolean CAN_BE_TOBJECT = false;

    public TArrayVersionLong(TObject.Version shared, int length) {
        super(shared, length);

        if (shared == null) {
            // Preallocate (C.f. TIndexedNVersion._writes)
            int arrayLength = getBits().length;
            _values = new long[arrayLength][];
        }
    }

    public final long[][] getValues() {
        return _values;
    }

    public final void setValues(long[][] value) {
        _values = value;
    }

    @Override
    public final TType[] getGenericParameters() {
        return _genericParameters;
    }

    public final long get(int index) {
        if (_values != null) {
            int foldedIndex = com.objectfabric.misc.Bits.getFoldedIntIndexFromIndex(getBits(), index);

            if (foldedIndex >= 0) {
                long[] current = _values[foldedIndex];

                if (current != null)
                    return current[index & com.objectfabric.misc.Bits.BIT_INDEX_MASK];
            }
        }

        return 0;
    }

    @Override
    public final Object getAsObject(int index) {
        return get(index);
    }

    public final void set(int index, long value) {
        if (_values == null) {
            if (value != 0) {
                int arrayLength = getBits().length;
                _values = new long[arrayLength][];
            }
        } else {
            if (com.objectfabric.misc.Debug.ENABLED)
                com.objectfabric.misc.Debug.assertion(_values.length == getBits().length);
        }

        if (_values != null) {
            int folded = com.objectfabric.misc.Bits.getFoldedIntIndexFromIndex(getBits(), index);

            if (_values[folded] == null) {
                if (value != 0) {
                    int arrayLength = com.objectfabric.misc.Bits.BITS_PER_UNIT;
                    _values[folded] = new long[arrayLength];
                }
            }

            if (_values[folded] != null)
                _values[folded][index & com.objectfabric.misc.Bits.BIT_INDEX_MASK] = value;
        }
    }

    @SuppressWarnings("cast")
    @Override
    public final void setAsObject(int index, Object value) {
        set(index, (Long) value);
    }

    @Override
    public final void reindexed(com.objectfabric.misc.Bits.Entry[] old) {
        if (_values != null) {
            long[][] oldValues = _values;
            int arrayLength = getBits().length;
            _values = new long[arrayLength][];

            for (int i = old.length - 1; i >= 0; i--) {
                if (old[i] != null) {
                    int intIndex = old[i].IntIndex;
                    int folded = com.objectfabric.misc.Bits.getFoldedIntIndexFromIntIndex(getBits(), intIndex);

                    if (com.objectfabric.misc.Debug.ENABLED)
                        com.objectfabric.misc.Debug.assertion(_values[folded] == null);

                    _values[folded] = oldValues[i];
                }
            }
        }
    }

    @Override
    public TObject.Version merge(TObject.Version target, TObject.Version next, int flags) {
        TArrayVersionLong source = (TArrayVersionLong) next;
        TArrayVersionLong merged = (TArrayVersionLong) super.merge(target, next, flags);
        merged.merge(source, flags);
        return merged;
    }

    @SuppressWarnings("cast")
    private final void merge(TArrayVersionLong source, int flags) {
        boolean skip1 = !(CAN_BE_TOBJECT && isShared()) && _values == null;

        if (skip1)
            if (getBits() != null && source.getBits() != null)
                if (getBits().length != source.getBits().length)
                    skip1 = false;

        if (skip1) {
            if (source._values != null) {
                if ((flags & MERGE_FLAG_COPY_ARRAYS) != 0) {
                    int arrayLength = source._values.length;
                    _values = new long[arrayLength][];
                    PlatformAdapter.arraycopy(source._values, 0, _values, 0, _values.length);
                } else
                    _values = source._values;
            }
        } else {
            com.objectfabric.misc.Bits.Entry[] writes = source.getBits();

            if (writes != null) {
                for (int i = writes.length - 1; i >= 0; i--) {
                    if (writes[i] != null && writes[i].Value != 0) {
                        if (_values == null) {
                            int arrayLength = getBits().length;
                            _values = new long[arrayLength][];
                        }

                        int folded = com.objectfabric.misc.Bits.getFoldedIntIndexFromIntIndex(getBits(), writes[i].IntIndex);

                        if (CAN_BE_TOBJECT && isShared()) {
                            if (_values[folded] == null) {
                                int arrayLength = com.objectfabric.misc.Bits.BITS_PER_UNIT;
                                _values[folded] = new long[arrayLength];
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
                                int arrayLength = com.objectfabric.misc.Bits.BITS_PER_UNIT;
                                _values[folded] = new long[arrayLength];
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

        if (com.objectfabric.misc.Debug.ENABLED)
            checkInvariants();
    }

    private final void merge(long[] merged, com.objectfabric.misc.Bits.Entry writes, long[] source) {
        for (int i = com.objectfabric.misc.Bits.BITS_PER_UNIT - 1; i >= 0; i--)
            if (com.objectfabric.misc.Bits.get(writes.Value, i))
                merged[i] = source != null ? source[i] : 0;
    }

    //

    @Override
    public final void writeWrite(Writer writer, int index) {
        if (writer.interrupted())
            writer.resume();

        if (!writer.canWriteLong()) {
            writer.interrupt(null);
            return;
        }

        writer.writeLong(get(index));
    }

    @Override
    public final void readWrite(Reader reader, int index) {
        if (reader.interrupted())
            reader.resume();

        if (!reader.canReadLong()) {
            reader.interrupt(null);
            return;
        }

        set(index, reader.readLong());
    }

    //

    @Override
    public TObject.Version createVersion() {
        return new TArrayVersionLong(this, length());
    }

    @Override
    public int getClassId() {
        if (com.objectfabric.misc.Debug.ENABLED)
            com.objectfabric.misc.Debug.assertion(length() >= 0);

        return -length() - 1;
    }

    //

    @SuppressWarnings("cast")
    @Override
    public void checkInvariants_() {
        super.checkInvariants_();

        if (getValues() != null) {
            com.objectfabric.misc.Debug.assertion(getValues().length == getBits().length);

            for (int i = 0; i < getValues().length; i++) {
                if (getValues()[i] != null) {
                    for (int t = 0; t < getValues()[i].length; t++) {
                        long value = getValues()[i][t];

                        if (value != 0) {
                            com.objectfabric.misc.Debug.assertion(com.objectfabric.misc.Bits.get(getBits()[i].Value, t));

                            if (isShared())
                                com.objectfabric.misc.Debug.assertion(!(((Object) value) instanceof UserTObject));
                            else
                                com.objectfabric.misc.Debug.assertion(!(((Object) value) instanceof TObject.Version));
                        }
                    }
                }
            }
        }
    }
}
// End (for .NET)
