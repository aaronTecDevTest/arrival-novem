package com.mexxon.database;

import com.mexxon.ImportExportMain;
import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */

public class DBManger {
    private static final Logger log = LogManager.getLogger(DBManger.class);
    private static ResourceBundle bundle = ImportExportMain.BUNDLE_CONFIG;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public DBManger() {
    }

    public static void main(String args[]) {
        DBConnection dbConnection = new DBConnection();
        String sql = "SELECT * FROM importexport.job_configuration;";
        DBManger dbManger = new DBManger();
        ArrayList<DBJobConfigEntity> listJobConfig = dbManger.getJobConfigTable(dbConnection.getConnection(), sql);

        //Test read from db
        for (DBJobConfigEntity jobConfigTable : listJobConfig) {
            System.out.println(jobConfigTable.getJobID());
            System.out.println(jobConfigTable.getJobTyp());
            System.out.println(jobConfigTable.getEndTime());
            System.out.println(jobConfigTable.endTimeProperty());
        }

        //Test write to db
        dbManger.setJobConfigTable(dbConnection.getConnection(),listJobConfig);
    }

    public void writeToDB(Connection connection, ArrayList<String> sqlList) throws SQLException {
        preparedStatement = connection.prepareStatement("");
        for (String sqlTemp: sqlList) {
            preparedStatement.execute(sqlTemp);
        }
    }

    public ResultSet readFromDB(Connection connection, String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        resultSet = preparedStatement.getResultSet();
        return resultSet;
    }

    public boolean setJobConfigTable(Connection connection, ArrayList<DBJobConfigEntity> dbJobConfigEntities) {
        boolean updateSuccessful = false;
        ArrayList<String> sqlList = new ArrayList<String>();

        try {
            for (DBJobConfigEntity data: dbJobConfigEntities){
                String sql = ImportExportMain.BUNDLE_SQL.getString("table.job_config.setData");
                //String sql = "INSERT INTO importexport.job_configuration VALUES";
                sql = sql +"("
                        +"NULL,"
                        +"'" + data.getJobTyp() +"',"
                        +"'" + data.getJobDescription() +"',"
                        +"'" + data.getTable() +"',"
                        +"'" + data.getSchema() +"',"
                        +"'" + data.getStartTime() +"',"
                        +"'" + data.getEndTime() +"',"
                        +"'" + data.getScheduler() +"',"
                        +"'" + data.getInterval() +"',"
                        +"'" + data.getFileExtension() +"',"
                        +"'" + data.getSeparator() +"',"
                        +"'" + data.getEncoding() +"',"
                        +"'" + data.getEmail() +"',"
                        +"'" + data.getHasHeader() +"',"
                        +"'" + data.getType() +"',"
                        +"'" + data.getPartner() +"',"
                        +"'" + data.getCreated() +"',"
                        +"'" + data.getLastModified() +"',"
                        +"'" + data.getIsDeleted() +"',"
                        +"'" + data.getUserName() +"',"
                        +"'" + data.getStatus() +"'"
                        +");";
                sqlList.add(sql);
            }
            writeToDB(connection, sqlList);
        } catch (SQLException e) {
            // something went wrong, we are handling the exception here
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e1) {
                }
            }
            // iterate and get all of the errors as much as possible.
            slqErrorMessage(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updateSuccessful;
    }

    public ArrayList<DBJobConfigEntity> getJobConfigTable (Connection connection, String sql){
        ArrayList<DBJobConfigEntity> jobConfigTables = new ArrayList<>();
        try {
            resultSet = readFromDB(connection, sql);
            while (resultSet.next()) {
                DBJobConfigEntity temp = new DBJobConfigEntity();
                temp.setJobID((long) resultSet.getInt(1));
                temp.setJobTyp(resultSet.getString(2));
                temp.setJobDescription(resultSet.getString(3));
                temp.setTable(resultSet.getString(4));
                temp.setSchema(resultSet.getString(5));
                temp.setStartTime(resultSet.getDate(6).toString() +" "+ resultSet.getTime(6).toString());
                temp.setEndTime(resultSet.getDate(7).toString() +" "+ resultSet.getTime(7).toString());
                temp.setScheduler(resultSet.getString(8));
                temp.setInterval(resultSet.getString(9));
                temp.setFileSource(resultSet.getString(10));
                temp.setFileExtension(resultSet.getString(11));
                temp.setSeparator(resultSet.getString(12));
                temp.setEncoding(resultSet.getString(13));
                temp.setEmail(resultSet.getString(14));
                temp.setHasHeader(resultSet.getString(15));
                temp.setType(resultSet.getString(16));
                temp.setPartner(resultSet.getString(17));
                temp.setCreated(resultSet.getDate(18).toString() +" "+ resultSet.getTime(18).toString());
                temp.setLastModified(resultSet.getDate(19).toString() +" "+ resultSet.getTime(19).toString());
                temp.setIsDeleted(resultSet.getString(20));
                temp.setUserName(resultSet.getString(21));
                temp.setStatus((long) resultSet.getInt(22));
                jobConfigTables.add(temp);
            }
        } catch (SQLException e) {
            // something went wrong, we are handling the exception here
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e1) {
                }
            }
            slqErrorMessage(e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jobConfigTables;
    }

    private void slqErrorMessage(SQLException e) {
            log.info("------------------------------------------ SQLException caught --------------------------------------");
        // iterate and get all of the errors as much as possible.
        while (e != null) {
            log.info("Message   : " + e.getMessage());
            log.info("SQLState  : " + e.getSQLState());
            log.info("ErrorCode : " + e.getErrorCode());
            log.info("------------------------------------------- SQLException end ----------------------------------------");
            e = e.getNextException();
        }
    }
}
