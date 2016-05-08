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

import static com.mexxon.process.EMProcessTyp.TABLE_TO_TABLE;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */

public class CSVTableToTable implements IFImportExport, Job, InterruptableJob{
    private static final Logger log = LogManager.getLogger(CSVTableToTable.class);
    private static final EMProcessTyp processTyp = TABLE_TO_TABLE;

    private DBJobConfigEntity jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/orderWithHeader.csv";

    public CSVTableToTable() {
    }

    public CSVTableToTable(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVTableToTable.class);
        this.processID = jobConfig.getJobID();
    }

    public static void main(String[] args) {
        CSVTableToTable csvTableToTable = new CSVTableToTable();
        csvTableToTable.setDBJobConfigTable(new DBJobConfigEntity());
        csvTableToTable.tableToTable();
    }

    public void tableToTable() {
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath), ';');

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();

            String insertQuery = "Insert into `order` (ClientOrder, POI, ProductID, ClientAccountID, AccountID, " +
                                                    "Gender, LastName, MaidenName,FirstName, Street, House, HouseADD, " +
                                                    "ZIP, City, Country, DOB, Phone, Email)" +
                    "values (null,?,?,?)";
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
        tableToTable();
    }
}