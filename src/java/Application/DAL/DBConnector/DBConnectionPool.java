package Application.DAL.DBConnector;

import Application.Utility.ObjectPool;

import java.io.IOException;
import java.sql.SQLException;


public class DBConnectionPool extends ObjectPool<DBConnection> {

    private static volatile DBConnectionPool instance;

    public DBConnectionPool() {
        super();
        instance = this;
    }

    @Override
    protected DBConnection create() {
        try {
            return new DBConnection();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(DBConnection o) {
        try {
            return (!o.getConnection().isClosed());
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public void expire(DBConnection o) {
        try {
            o.getConnection().close();
        } catch (SQLException e) {
        }
    }

    /**
     * private static class.
     * Thread-safe lazy initialization is achieved without explicit synchronization.
     * the variable INSTANCE is wrapped in an inner class, utilizing the class loader to do synchronization.
     * The class loader guarantees to complete all static initialization before it grants access to the class.
     * This implementation lazy initializes the INSTANCE by calling LoadSingleton.INSTANCE
     * when first accessed inside getInstance() method.
     */
    private static class LoadSingleton {
        private static final DBConnectionPool INSTANCE = new DBConnectionPool();

        private LoadSingleton() {}

        public static DBConnectionPool getInstance() {
            return INSTANCE;
        }
    }

    public static DBConnectionPool getInstance() {
        return LoadSingleton.getInstance();
    }
}
