package com.mexxon.process;

import com.mexxon.database.DAO.DBOrderDao;
import com.mexxon.database.entity.DBOrderEntity;
import com.mexxon.scheduler.JobExecution;
import com.mexxon.windows.model.DBJobConfigEntity;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private String filePath = "../arrival-novem/src/main/resources/testingData/orderWithHeader.csv";

    public CSVImportProcess() {
    }

    public CSVImportProcess(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVImportProcess.class);
        this.processID = jobConfig.getJobID();
    }

    public static void main(String[] args) {
        CSVImportProcess csvImportProcess = new CSVImportProcess();
        DBJobConfigEntity jobConfig = new DBJobConfigEntity();
        jobConfig.setSeparator(";");
        csvImportProcess.setDBJobConfigTable(jobConfig);
        csvImportProcess.importCSV();
    }

    public void importCSV() {
        try {
            /**
             * http://opencsv.sourceforge.net/
             * Header
             * ClientOrder;POI;ProductID;ClientAccountID;AccountID;Gender;LastName;MaidenName;firstName;Street;House;HouseADD;ZIP;City;Country;DOB;Phone;Email
             * */
            Character separator = jobConfig.getSeparator().charAt(0);
            /*
              Skipp the first line
             */
            CSVReader reader = new CSVReader(new FileReader(filePath), separator, '\"', 1);

            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(DBOrderEntity.class);

            /**
             * Column Mapping
             */
            Map<String, Integer> testData = new HashMap();
            testData.put("ClientOrder",0);
            testData.put("ZIP",3);
            testData.put("City",5);
            testData.put("Gender",6);
            testData.put("Street",7);
            testData.put("HouseADD",9);
            testData.put("Phone",10);
            testData.put("POI",11);

            //String[] columns = new DBOrderEntity().getConfigHeader(testData);
            String[] columns = new DBOrderEntity().getDefaultHeader();

            mappingStrategy.setColumnMapping(columns);

            CsvToBean csv = new CsvToBean();
            List list = csv.parse(mappingStrategy, reader);

            ArrayList<Object> dataEntity = new ArrayList<>(list);
            DBOrderDao dbAccess = new DBOrderDao();
            dbAccess.writeItemsToDB(dataEntity);

            log.info("Data Successfully import!");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /***
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
**/

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
//      JobExecution.jobInterruption();
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
        importCSV();
    }
}