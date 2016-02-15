package by.epam.mentoring.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCredentials {

    private static final Logger LOGGER = LoggerFactory.getLogger(DBCredentials.class);

    private String driver;
    private String connectionString;
    private String user;
    private String password;
    private String dbName;

    public DBCredentials(String driver, String connectionString, String user, String password, String dbName) {
        this.driver = driver;
        this.connectionString = connectionString;
        this.user = user;
        this.password = password;
        this.dbName = dbName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public Connection getDBConnection(String dbName) {
        Connection dbConnection = null;
        try {
            Class.forName(driver);
            dbConnection = DriverManager.getConnection(connectionString + dbName, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Can't resolve driver or open connection.", e);
        }
        return dbConnection;
    }
}
