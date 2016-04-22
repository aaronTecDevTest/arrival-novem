package com.mexxon.database;

import com.mexxon.utilities.SystemPreferences;
import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */

public class DBManger {
    private static final Logger log = LogManager.getLogger(DBManger.class);
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
            System.out.println(jobConfigTable.getJob_id());
            System.out.println(jobConfigTable.getJob_typ());
            System.out.println(jobConfigTable.getEnd_time());
            System.out.println(jobConfigTable.end_timeProperty());
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
                String sql = SystemPreferences.getResourceBundle("arrivalSQL").getString("table.job_config.setData");
                sql = sql +"("
                        +"NULL,"
                        +"'" + data.getJob_typ() +"',"
                        +"'" + data.getJob_description() +"',"
                        +"'" + data.getFrom() +"',"
                        +"'" + data.getTo() +"',"
                        +"'" + data.getStart_time() +"',"
                        +"'" + data.getEnd_time() +"',"
                        +"'" + data.getScheduler() +"',"
                        +"'" + data.getExpired_time() +"',"
                        +"'" + data.getExport_sql() +"',"
                        +"'" + data.getCsv_separator() +"'"
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
                temp.setJob_id(resultSet.getInt(1));
                temp.setJob_typ(resultSet.getString(2));
                temp.setJob_description(resultSet.getString(3));
                temp.setFrom(resultSet.getString(4));
                temp.setTo(resultSet.getString(5));
                temp.setStart_time(resultSet.getDate(6).toString() +" "+ resultSet.getTime(6).toString());
                temp.setEnd_time(resultSet.getDate(7).toString() +" "+ resultSet.getTime(7).toString());
                temp.setScheduler(resultSet.getString(8));
                temp.setExpired_time(resultSet.getString(9));
                temp.setExport_sql(resultSet.getString(10));
                temp.setCsv_separator(resultSet.getString(11));
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
