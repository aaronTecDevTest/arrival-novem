package com.mexxon.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */

public class DBManger {
    private static final Logger log = LogManager.getLogger(DBManger.class);

    static final String WRITE_OBJECT_SQL = "INSERT INTO java_objects(name, object_value) VALUES (?, ?)";

    static final String READ_OBJECT_SQL = "SELECT object_value FROM java_objects WHERE id = ?";

    private ArrayList<String>  sqlTabelSeceltList = new ArrayList<String>();

    public DBManger() {
    }


    public static long writeJavaObject(Connection conn, Object object) throws Exception {
        String className = object.getClass().getName();
        PreparedStatement pstmt = conn.prepareStatement(WRITE_OBJECT_SQL, Statement.RETURN_GENERATED_KEYS);

        // set input parameters
        pstmt.setString(1, className);
        pstmt.setObject(2, object);
        pstmt.executeUpdate();

        // get the generated key for the id
        ResultSet rs = pstmt.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        rs.close();
        pstmt.close();
        System.out.println("writeJavaObject: done serializing: " + className);
        return id;
    }

    public static Object readJavaObject(Connection conn, long id) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(READ_OBJECT_SQL);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        Object object = rs.getObject(1);
        String className = object.getClass().getName();

        rs.close();
        pstmt.close();
        System.out.println("readJavaObject: done de-serializing: " + className);
        return object;
    }
    public static void main(String args[])throws Exception {
        /**
         CREATE TABLE java_objects (
         id INT AUTO_INCREMENT,
         name varchar(128),
         object_value BLOB,
         primary key (id));
         **/

        Connection conn = null;
        try {
            DBConnection dbConnection = new DBConnection();

            conn = dbConnection.getConnection();
            System.out.println("conn=" + conn);
            conn.setAutoCommit(false);
            List<Object> list = new ArrayList<Object>();
            list.add("This is a short string.");
            list.add(new Integer(1234));
            list.add(new Date());

            long objectID = writeJavaObject(conn, list);
            conn.commit();
            System.out.println("Serialized objectID => " + objectID);
            List listFromDatabase = (List) readJavaObject(conn, objectID);
            System.out.println("[After De-Serialization] list=" + listFromDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
