package com.mexxon.process;

import com.mexxon.scheduler.JobExecution;
import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;

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

    private DBJobConfigEntity jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    public CSVExportMexxonProcess() {
    }

    public CSVExportMexxonProcess(DBJobConfigEntity jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportMexxonProcess.class);
        this.processID = jobConfig.getJob_id();
    }

    public void exportMexxonCSV(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @Override
    public JobBuilder getJobBuilder() {
        return jobBuilder;
    }

    @Override
    public void runJob() {
        exportMexxonCSV();
    }
}