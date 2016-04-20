package com.mexxon.process;

import com.mexxon.database.DBConnection;
import com.mexxon.windows.model.DBJobConfigTable;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static com.mexxon.process.EMProcessTyp.IMPORT_SQL;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class CSVImportSQLProcess implements IFImportExport, Job{
    private static final Logger log = LogManager.getLogger(CSVImportSQLProcess.class);
    private static final EMProcessTyp processTyp = IMPORT_SQL;

    private DBJobConfigTable jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/fileToImport.csv";

    public CSVImportSQLProcess() {
    }

    public CSVImportSQLProcess(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVImportSQLProcess.class);
        this.processID = jobConfig.getJob_id();
    }

    public static void main(String[] args) {
        /**
         *
         CREATE TABLE `txn_tbl` (
         `txn_id`  int(11) NOT NULL AUTO_INCREMENT ,
         `txn_amount`  double NOT NULL ,
         `card_number`  bigint(20) NOT NULL ,
         `terminal_id`  bigint(20) NULL DEFAULT NULL ,
         PRIMARY KEY (`txn_id`))
         */
        CSVImportSQLProcess csvImportProcess = new CSVImportSQLProcess();
        csvImportProcess.setDBJobConfigTable(new DBJobConfigTable());
        csvImportProcess.importCSV();
    }

    public void importCSV() {
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath), ',');

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();

            String insertQuery = "Insert into txn_tbl (txn_id,txn_amount, card_number, terminal_id) values (null,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            String[] rowData = null;
            int i = 0;
            while ((rowData = reader.readNext()) != null) {
                for (String data : rowData) {
                    preparedStatement.setString((i % 3) + 1, data);

                    if (++i % 3 == 0)
                        preparedStatement.addBatch();// add batch

                    if (i % 30 == 0)// insert when the batch size is 10
                        preparedStatement.executeBatch();
                }
            }
            connection.close();
            log.info("Data Successfully import!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setDBJobConfigTable(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportMexxonProcess.class);
        this.processID = jobConfig.getJob_id();
    }

    @Override
    public DBJobConfigTable getJobConfig() {
        return jobConfig;
    }

    @Override
    public void setJobConfig(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
    }

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetail jobDetail = jobContext.getJobDetail();

        IFImportExport csvImportExport = (IFImportExport) jobDetail.getJobDataMap().get("csvImportExport");

        log.info("Job ID: " + csvImportExport.getJobConfig().getJob_id());
        log.info("--------------------------------------------------------------------");
        log.info("JobExecution start: " + jobContext.getFireTime());
        log.info("Job name is: " + jobDetail.getJobDataMap().getString(csvImportExport.getClass().getSimpleName()));

        importCSV();

        log.info("JobExecution end: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        log.info("JobExecution next scheduled time: " + jobContext.getNextFireTime());
        log.info("--------------------------------------------------------------------\n");
    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @Override
    public JobBuilder getJobBuilder() {
        return jobBuilder;
    }
}