package com.mexxon.utilities;

import com.mexxon.database.DBConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.utilities
 */

public class Authentication {
    private static final Logger log = LogManager.getLogger(Authentication.class);
    private static Authentication ourInstance = new Authentication();

    private String username;
    private String userpassword;
    private boolean loginStatus;
    private static DBConnection dbConnection;

    private Authentication() {
        log.info(Authentication.class + " is loaded!!");
        username = null;
        userpassword = null;
        loginStatus = false;
        dbConnection = new DBConnection();
    }

    public static Authentication getInstance() {
        return ourInstance;
    }

    public boolean login(String username,String userpassword ){
        this.username = username;
        this.userpassword = userpassword;
        Connection connection = dbConnection.getConnection(username, userpassword);
        if(connection != null){
            try {
                if (!connection.isClosed())
                     loginStatus = true;
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
        return loginStatus;
    }

    public boolean logout(){
        Connection connection = dbConnection.closeConnection();
        try {
            if(connection.isClosed())
                loginStatus = false;
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return loginStatus;
    }

    public boolean getLoginStatus(){
        return loginStatus;
    }

    public static DBConnection getDbConnection(){
        return dbConnection;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
    /*
    public static void main(String[] args) {
        Authentication authentication = Authentication.getInstance();
        authentication.login("test","test");
        log.info("LogStatus:" + authentication.getLoginStatus());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        authentication.logout();
        log.info("LogStatus: " + authentication.getLoginStatus());
    }
  */
}
