package com.mexxon.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 01.04.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

public class JobExecution implements Job{
    private static final Logger log = LogManager.getLogger(JobExecution.class);
    private static int count;

    public void execute(JobExecutionContext jobContext) throws JobExecutionException {
        JobDetail jobDetail = jobContext.getJobDetail();

        IFLatch latch = (IFLatch) jobDetail.getJobDataMap().get("latch");
        latch.countDown();
        count++;
        log.info("Job count " + count);
        log.info("--------------------------------------------------------------------");
        log.info("JobExecution start: " + jobContext.getFireTime());
        log.info("Job name is: " + jobDetail.getJobDataMap().getString("example"));

        //TODO: Latter here
        //jobToDo();
        //TODO: Latter here

        log.info("JobExecution end: " + jobContext.getJobRunTime() + ", key: " + jobDetail.getKey());
        log.info("JobExecution next scheduled time: " + jobContext.getNextFireTime());
        log.info("--------------------------------------------------------------------\n");

        jobToDo();
    }

    void jobToDo()throws JobExecutionException {

        if (count == 2) {
            throw new RuntimeException("Some RuntimeException!");
        }
        if (count == 4) {
            throw new JobExecutionException("Some JobExecutionException!");
        }
    }
}
