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

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                        
//                                                                              
//==============================================================================

/**
 * Transactional array. ObjectFabric generates implementations for every supported type (
 * {@link Immutable} and {@link TObjects}) for faster serialization, to avoid boxing
 * primitive types, and for easier interoperability with .NET generics which require a
 * type at runtime.
 */
public class TArrayCharacter extends TIndexed implements Iterable<Character> {

    public static final TType TYPE;

    static {
        TYPE = Platform.newTType(Platform.get().defaultObjectModel(), -1, Immutable.CHARACTER.type());
    }

    private final int _length;

    private final TType[] _genericParameters;

    private static final boolean IS_TOBJECT = false;

    private static final boolean CAN_BE_TOBJECT = false;

    public TArrayCharacter(Resource resource, int length) {
        super(resource, new TArrayVersionCharacter(length));

        _length = length;
        _genericParameters = new TType[] { Immutable.CHARACTER.type() };
    }

    /**
     * This constructor is only useful if the object might get replicated to a .NET
     * process, to specify which type would be instantiated by the remote runtime.
     */
    /* generic start public TArrayCharacter(Resource resource, int length, TType genericParam) {
        super(resource, new TArrayVersionCharacter(length));

        _length = length;

        TType[] value = new TType[] { Immutable.CHARACTER.type() };

        if (genericParam != null) {
            if (genericParam.getObjectModel() == null)
                throw new IllegalArgumentException("Generic parameter must be a transactional object.");

            value = new TType[] { genericParam };
        }

        _genericParameters = value;
    } generic end */

    @Override
    final TType[] genericParameters() {
        return _genericParameters;
    }

    @Override
    public final int length() {
        return _length;
    }

    @SuppressWarnings({ "cast", "unchecked" })
    public final char get(int index) {
        if (index < 0 || index >= length())
            throw new IndexOutOfBoundsException();

        Transaction outer = current_();
        Transaction inner = startRead_(outer);
        TArrayVersionCharacter version = (TArrayVersionCharacter) getVersionN_(inner, index);
        char value = version != null ? (char) version.get(index) : '\0';
        endRead_(outer, inner);
        return value;
    }

    @SuppressWarnings("cast")
    public final void set(int index, char value) {
        if (index < 0 || index >= length())
            throw new IndexOutOfBoundsException();

        Object asObject = (Object) value;

        if (IS_TOBJECT) {
            if (asObject != null && ((TObject) asObject).resource() != resource())
                wrongResource_();
        } else if (CAN_BE_TOBJECT) {
            if (asObject instanceof TObject && ((TObject) asObject).resource() != resource())
                wrongResource_();
        }

        Transaction outer = current_();
        Transaction inner = startWrite_(outer);
        TArrayVersionCharacter version = (TArrayVersionCharacter) getOrCreateVersion_(inner);
        version.setBit(index);
        version.set(index, value);
        endWrite_(outer, inner);
    }

    @Override
    public final java.util.Iterator<Character> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements java.util.Iterator<Character> {

        private int _cursor = 0;

        @Override
        public boolean hasNext() {
            return _cursor != _length;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Character next() {
            Character value = get(_cursor++);
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    protected final TObject.Version createVersion_() {
        TArrayVersionCharacter version = new TArrayVersionCharacter(0);
        version.setObject(this);
        return version;
    }

    @Override
    protected final int classId_() {
        if (org.objectfabric.Debug.ENABLED)
            org.objectfabric.Debug.assertion(length() >= 0);

        return -length() - 1;
    }
}