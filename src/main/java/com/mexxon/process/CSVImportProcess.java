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

public class CSVImportProcess {
    private static final Logger log = LogManager.getLogger(CSVImportProcess.class);
    private FileReader fileReader;

    public void readCSV() {
        try {
            CSVReader reader = new CSVReader(new FileReader("upload.csv"), ',');
            Connection connection = DBConnection.getConnection();

            String insertQuery = "Insert into txn_tbl (txn_id,txn_amount, card_number, terminal_id) values (null,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(insertQuery);
            String[] rowData = null;
            int i = 0;
            while ((rowData = reader.readNext()) != null) {
                for (String data : rowData) {
                    pstmt.setString((i % 3) + 1, data);

                    if (++i % 3 == 0)
                        pstmt.addBatch();// add batch

                    if (i % 30 == 0)// insert when the batch size is 10
                        pstmt.executeBatch();
                }
            }
            System.out.println("Data Successfully Uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void readCSVUsingLoad() {
        try (Connection connection = DBConnection.getConnection()) {
            String loadQuery = "LOAD DATA LOCAL INFILE '"
                    + "C:\\upload.csv"
                    + "' INTO TABLE txn_tbl FIELDS TERMINATED BY ','"
                    + " LINES TERMINATED BY '\n' (txn_amount, card_number, terminal_id) ";

            System.out.println(loadQuery);
            Statement stmt = connection.createStatement();
            stmt.execute(loadQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * CREATE TABLE `txn_tbl` (
         `txn_id`  int(11) NOT NULL AUTO_INCREMENT ,
         `txn_amount`  double NOT NULL ,
         `card_number`  bigint(20) NOT NULL ,
         `terminal_id`  bigint(20) NULL DEFAULT NULL ,
         PRIMARY KEY (`txn_id`)
         )
         */
        CSVImportProcess csvImportPorcess = new CSVImportProcess();
        csvImportPorcess.readCSV();
        csvImportPorcess.readCSVUsingLoad();
    }
}
