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

import static com.mexxon.process.EMProcessTyp.IMPORT;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class CSVImportProcess implements IFImportExport, Job{
    private static final Logger log = LogManager.getLogger(CSVImportProcess.class);
    private static final EMProcessTyp processTyp = IMPORT;

    private DBJobConfigTable jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/fileToImport.csv";

    public CSVImportProcess(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVImportProcess.class);
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
        CSVImportProcess csvImportProcess = new CSVImportProcess( new DBJobConfigTable() );
        //csvImportProcess.importCSV();
        csvImportProcess.importCSVUsingDBLoad();
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

    public void importCSVUsingDBLoad() {
        DBConnection dbConnection = new DBConnection();

        try (Connection connection = dbConnection.getConnection()) {
            String loadQuery =
                    "LOAD DATA LOCAL INFILE '"
                            + filePath
                            + "' INTO TABLE txn_tbl "
                            + "FIELDS TERMINATED BY ','"
                            + " LINES TERMINATED BY '\n' "
                            + "(txn_amount, card_number, terminal_id) ";

            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
            log.info(loadQuery);
            log.info("Data Successfully import!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public DBJobConfigTable getJobConfig() {
        return jobConfig;
    }

    @Override
    public void setJobConfig(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
    }

    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetail jobDetail = jobContext.getJobDetail();

        IFImportExport csvImportExport = (IFImportExport) jobDetail.getJobDataMap().get("csvImportExport");

        log.info("Job ID: " + csvImportExport.getJobConfig().getJob_id());
        log.info("--------------------------------------------------------------------");
        log.info("JobExecution start: " + jobContext.getFireTime());
        log.info("Job name is: " + jobDetail.getJobDataMap().getString("example"));

        importCSVUsingDBLoad();

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
