package com.mexxon.process;

import com.mexxon.database.DBConnection;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class CSVImportSQLProcess extends CSVExportImport implements IFImportExport{
    private static final Logger log = LogManager.getLogger(CSVImportSQLProcess.class);
    private static Long processID;

    private FileReader fileReader;
    private String filePath = "../arrival-novem/src/main/resources/testingData/fileToImport.csv";

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
            log.error(e.getStackTrace());
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
            log.error(e.getStackTrace());
        }
    }


    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }
}
