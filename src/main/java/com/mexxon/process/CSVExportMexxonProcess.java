package com.mexxon.process;

import com.mexxon.windows.model.DBJobConfigTable;
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

    private DBJobConfigTable jobConfig;
    private JobBuilder jobBuilder;
    private Long processID;

    public CSVExportMexxonProcess(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
        this.jobBuilder = JobBuilder.newJob(CSVExportMexxonProcess.class);
        this.processID = jobConfig.getJob_id();
    }

    public void exportMexxonCSV(){

    }

    @Override
    public DBJobConfigTable getJobConfig() {
        return jobConfig;
    }

    @Override
    public void setJobConfig(DBJobConfigTable jobConfig) {
        this.jobConfig = jobConfig;
    }

    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetail jobDetail = jobContext.getJobDetail();

        IFImportExport csvImportExport = (IFImportExport) jobDetail.getJobDataMap().get("csvImportExport");

        log.info("Job ID: " + csvImportExport.getJobConfig().getJob_id());
        log.info("--------------------------------------------------------------------");
        log.info("JobExecution start: " + jobContext.getFireTime());
        log.info("Job name is: " + jobDetail.getJobDataMap().getString("example"));

        exportMexxonCSV();

        log.info("JobExecution end: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        log.info("JobExecution next scheduled time: " + jobContext.getNextFireTime());
        log.info("--------------------------------------------------------------------\n");
    }

    @Override
    public void setProcessID(Long processID) {
        this.processID = processID;
    }

    @Override
    public JobBuilder getJobBuilder() {
        return jobBuilder;
    }
}