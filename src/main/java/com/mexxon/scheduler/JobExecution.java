package com.mexxon.scheduler;

import com.mexxon.process.IFImportExport;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 22.04.2016.
 * @since: 1.0
 * Package: com.mexxon.scheduler
 */
public class JobExecution {

    public JobExecution() {
    }

    public static void jobExecution(JobExecutionContext jobContext, Logger log){
        JobDetail jobDetail = jobContext.getJobDetail();

        IFImportExport csvImportExport = (IFImportExport) jobDetail.getJobDataMap().get("csvImportExport");
        String jobName =jobDetail.getJobDataMap().getString(csvImportExport.getClass().getSimpleName());

        log.info("Job ID: " + csvImportExport.getJobConfig().getJob_id());
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("JobExecution start: " + jobContext.getFireTime());
        log.info("Job name is: " + jobName);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        csvImportExport.runJob();

        log.info("--------------------------------------------------------------------");
        log.info("--------------------------------------------------------------------");
        log.info("Job name is: " + jobName);
        log.info("JobExecution end: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        log.info("JobExecution next scheduled time: " + jobContext.getNextFireTime());
        log.info("--------------------------------------------------------------------");
        log.info("--------------------------------------------------------------------");
    }

    public static  void jobInterruption(){
        Thread tread = Thread.currentThread();
        if (tread != null)
            tread.interrupt();
    }
}
