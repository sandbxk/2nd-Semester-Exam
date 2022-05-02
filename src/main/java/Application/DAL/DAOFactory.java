package Application.DAL;

import Application.DAL.DBConnector.DBConnectionPool;

import java.sql.ResultSet;

public abstract class DAOFactory {


    private DBConnectionPool connectionPool;

    public abstract void insert(String query);
    public abstract ResultSet read(String query);
    public abstract void update(String query);
    public abstract void delete(String query);


}
