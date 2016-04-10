package com.mexxon.process;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */


public interface IFImportExport {

    void importFromCSV();
    void exportToCSV();

    void readCSV();
    void writeCSV();

    void readDB();
    void writeDB();
}
