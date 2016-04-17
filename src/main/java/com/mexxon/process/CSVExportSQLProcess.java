package com.mexxon.process;

import com.mexxon.database.DBConnection;
import com.mexxon.windows.model.DBJobConfigTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.mexxon.process.EMProcessTyp.EXPORT_WITH_SQL;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class CSVExportSQLProcess implements IFImportExport, Job{
    private static final Logger log = LogManager.getLogger(CSVExportSQLProcess.class);
    private static final EMProcessTyp processTyp = EXPORT_WITH_SQL;


    private static Long processID;

    public CSVExportSQLProcess(){

    }

    public void exportToCSV(){
        String filename ="Desktop:test.csv";
        try {
            FileWriter fw = new FileWriter(filename);
            Connection conn = new DBConnection().getConnection();
            String query = "select * from testtable";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                fw.append(rs.getString(1));
                fw.append(',');
                fw.append(rs.getString(2));
                fw.append(',');
                fw.append(rs.getString(3));
                fw.append('\n');
            }
            fw.flush();
            fw.close();
            conn.close();
            System.out.println("CSV File is created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setJobConfig(DBJobConfigTable jobConfig) {

    }

    @Override
    public DBJobConfigTable getJobConfig() {
        return null;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    @Override
    public void runProcess() {

    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }
}
