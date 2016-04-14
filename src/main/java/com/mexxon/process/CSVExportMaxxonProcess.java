package com.mexxon.process;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.04.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */
public class CSVExportMaxxonProcess extends CSVExportImport implements IFImportExport{
    private static final Logger log = LogManager.getLogger(CSVExportMaxxonProcess.class);
    private static Long processID;

    public CSVExportMaxxonProcess() {
    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }
}
