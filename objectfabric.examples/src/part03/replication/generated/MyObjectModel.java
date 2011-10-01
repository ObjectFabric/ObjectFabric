
package part03.replication.generated;

//==============================================================================
//                                                                              
//  THIS FILE HAS BEEN GENERATED BY OBJECTFABRIC                                
//                                                                              
//==============================================================================

@SuppressWarnings({ "hiding", "unchecked", "static-access" })
public class MyObjectModel extends com.objectfabric.ObjectModel {

    private static final byte[] UID = { 102, -87, -46, 48, -108, 109, -93, -69, -41, 17, -8, -125, -20, -126, 75, -4 };

    private static volatile MyObjectModel _instance;

    private static final Object _lock = new Object();

    protected MyObjectModel(com.objectfabric.TObject.Version shared) {
        super(shared);
    }

    protected MyObjectModel() {
        this(new Version(null));
    }

    public static MyObjectModel getInstance() {
        if (_instance == null) {
            synchronized (_lock) {
                if (_instance == null)
                    _instance = new MyObjectModel();
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
    public static void registerOverridenModel(MyObjectModel model) {
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

    public static final int PART03_REPLICATION_GENERATED_MYCLASS_CLASS_ID = 0;

    public static final int METHOD_COUNT = 0;

    @Override
    protected java.lang.Class getClass(int classId, com.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case PART03_REPLICATION_GENERATED_MYCLASS_CLASS_ID:
                return part03.replication.generated.MyClass.class;
        }

        return super.getClass(classId, genericParameters);
    }

    @Override
    protected com.objectfabric.TObject.UserTObject createInstance(com.objectfabric.Transaction trunk, int classId, com.objectfabric.TType[] genericParameters) {
        switch (classId) {
            case PART03_REPLICATION_GENERATED_MYCLASS_CLASS_ID:
                return new part03.replication.generated.MyClass(trunk);
        }

        return super.createInstance(trunk, classId, genericParameters);
    }

    protected static final class Version extends com.objectfabric.ObjectModel.Version {

        public Version(com.objectfabric.ObjectModel.Version shared) {
            super(shared);
        }

        @Override
        public byte[] getUID() {
            return MyObjectModel.UID;
        }
    }
}
