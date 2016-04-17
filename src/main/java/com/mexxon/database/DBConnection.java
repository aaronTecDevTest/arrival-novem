package com.mexxon.database;

import com.mexxon.utilities.SystemPreferences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 06.04.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */
public class DBConnection {
    private static final Logger log = LogManager.getLogger(DBConnection.class);
    private static ResourceBundle bundle = SystemPreferences.getResourceBundle("arrivalConfig");

    private Connection connection = null;
    private String url = bundle.getString("db.url.host");
    private String username = bundle.getString("db.user.name");
    private String password = bundle.getString("db.user.password");

    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        dbConnection.getConnection();
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dbConnection.closeConnection();
    }

    /**
     * @return connection for adminConfig
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            log.info("Successfully login to MySQLDB!");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage());
        }
        return connection;
    }

    /**
     * @return connection if Username and Password is right
     */
    public Connection getConnection(String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.username = username;
            this.password = password;

            connection = DriverManager.getConnection(url, username, password);
            log.info("Successfully login to MySQLDB!");
        } catch (SQLException e) {
            log.info(e.getMessage());
        } catch (ClassNotFoundException ex) {
            log.error(ex.getMessage());
        }
        return connection;
    }

    /**
     * close the DB connection
     */
    public Connection closeConnection() {
        try {
            connection.close();
            log.info("Successfully logout from MySQLDB!");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return connection;
    }
}
