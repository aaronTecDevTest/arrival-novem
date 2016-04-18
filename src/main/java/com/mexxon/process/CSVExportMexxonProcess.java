package com.mexxon.process;

import com.mexxon.windows.model.DBJobConfigTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import static com.mexxon.process.EMProcessTyp.EXPORT_MEXXON_CSV;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 14.04.2016.
 * @since: 1.0
 * Package: com.mexxon.process
 */
public class CSVExportMexxonProcess implements IFImportExport, Job {
    private static final Logger log = LogManager.getLogger(CSVExportMexxonProcess.class);
    private static final EMProcessTyp processTyp = EXPORT_MEXXON_CSV;
    private static Long processID;




    public CSVExportMexxonProcess() {

    }

    @Override
    public void runProcess() {
        try {
            log.info("run is am laufen!!");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DBJobConfigTable getJobConfig() {
        return null;
    }

    @Override
    public void setJobConfig(DBJobConfigTable jobConfig) {

    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }
}
