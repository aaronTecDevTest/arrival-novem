package com.mexxon.process;

import com.mexxon.database.DBConnection;
import com.mexxon.scheduler.JobExecution;
import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

import static com.mexxon.process.EMProcessTyp.IMPORT;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class CSVImportProcess implements IFImportExport, Job, InterruptableJob{
    private static final Logger log = LogManager.getLogger(CSVImportProcess.class);
    private static final EMProcessTyp processTyp = IMPORT;

    private DBJobConfigEntity jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/fileToImport.csv";

    public CSVImportProcess() {
    }

    public CSVImportProcess(DBJobConfigEntity jobConfig) {
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
        CSVImportProcess csvImportProcess = new CSVImportProcess();
        csvImportProcess.setDBJobConfigTable(new DBJobConfigEntity());
        csvImportProcess.importCSVUsingDBLoad();
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
            log.info("SQL-Query:" + loadQuery);
            log.info("Data Successfully import!");
            Thread.sleep(20000);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setDBJobConfigTable(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportMexxonProcess.class);
        this.processID = jobConfig.getJob_id();
    }

    @Override
    public DBJobConfigEntity getJobConfig() {
        return jobConfig;
    }

    @Override
    public void setJobConfig(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
    }

    @Override
    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobExecution.jobExecution(jobContext,log);
    }

    @Override
    public void interrupt() throws UnableToInterruptJobException {
        JobExecution.jobInterruption();
    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @Override
    public JobBuilder getJobBuilder() {
        return jobBuilder;
    }

    @Override
    public void runJob() {
        importCSVUsingDBLoad();
    }
}
