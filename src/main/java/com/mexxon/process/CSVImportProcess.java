package com.mexxon.process;

import com.mexxon.database.DBConnection;
import com.mexxon.scheduler.JobExecution;
import com.mexxon.windows.model.DBJobConfigEntity;
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

public class CSVImportProcess implements IFImportExport, Job, InterruptableJob{
    private static final Logger log = LogManager.getLogger(CSVImportProcess.class);
    private static final EMProcessTyp processTyp = IMPORT;

    private DBJobConfigEntity jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/order.csv";

    public CSVImportProcess() {
    }

    public CSVImportProcess(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVImportProcess.class);
        this.processID = jobConfig.getJobID();
    }

    public static void main(String[] args) {
        CSVImportProcess csvImportProcess = new CSVImportProcess();
        csvImportProcess.setDBJobConfigTable(new DBJobConfigEntity());
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
                            + "'INTO TABLE `order` "
                            + "FIELDS TERMINATED BY ';'"
                            + "LINES TERMINATED BY '\n' "
                            + "(ClientOrder, POI, ProductID, ClientAccountID, AccountID, Gender, LastName, MaidenName, "
                            + "FirstName, Street, House, HouseADD, ZIP, City, Country, DOB, Phone, Email)";

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
        this.processID = jobConfig.getJobID();
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
//        JobExecution.jobInterruption();

        Thread tread = Thread.currentThread();
        if (tread != null)
            tread.interrupt();

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