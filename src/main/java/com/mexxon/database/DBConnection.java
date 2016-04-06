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

    private static Connection connection = null;
    private static String url = bundle.getString("db.url.host");
    private static String username = bundle.getString("db.user.name");
    private static String password= bundle.getString("db.user.password");

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        //Class.forName("com.mysql.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(url, username, password);
            log.info("Successfully login to MySQLDB!");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            log.info("Successfully logout from MySQLDB!");
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
    }

    public static void main(String[] args) {
        getConnection();
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closeConnection();
    }
}
