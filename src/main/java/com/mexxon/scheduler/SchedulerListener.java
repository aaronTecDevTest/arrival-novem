package com.mexxon.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 01.04.2016.
 * @since: 1.0
 * Package: com.mexxon.scheduler
 */

public class SchedulerListener implements org.quartz.SchedulerListener{
    private static final Logger log = LogManager.getLogger(SchedulerListener.class);
    private final Scheduler scheduler;

    public SchedulerListener(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void jobScheduled(Trigger trigger) {
       log.info("Job scheduled: " + trigger.getKey().getName());
    }

    public void jobUnscheduled(TriggerKey triggerKey) {
       log.info("Job Unscheduled: " + triggerKey.getName());
    }

    public void triggerFinalized(Trigger trigger) {
       log.info("Job trigger finalized: "
                + trigger.getKey().getName());
    }

    public void triggerPaused(TriggerKey triggerKey) {
       log.info("Job trigger paused: " + triggerKey.getName());
    }

    public void triggersPaused(String triggerGroup) {
       log.info("Job triggers paused for trigger group: "
                + triggerGroup);
    }

    public void triggerResumed(TriggerKey triggerKey) {
       log.info("Job triggers resumed for trigger: " + triggerKey);
    }

    public void triggersResumed(String triggerGroup) {
       log.info("Job triggers resumed for trigger group: "
                + triggerGroup);
    }

    public void jobAdded(JobDetail jobDetail) {
       log.info("Job added: " + jobDetail.getKey().getName());
    }

    public void jobDeleted(JobKey jobKey) {
       log.info("Job deleted: " + jobKey.getName());
    }

    public void jobPaused(JobKey jobKey) {
       log.info("Jobs paused for job: " + jobKey);
    }

    public void jobsPaused(String jobGroup) {
       log.info("Jobs paused for job group: " + jobGroup);
    }

    public void jobResumed(JobKey jobKey) {
       log.info("Job resumed: " + jobKey.getName());
    }

    public void jobsResumed(String jobGroup) {
       log.info("Jobs resumed for job group: " + jobGroup);
    }

    public void schedulerError(String msg, SchedulerException cause) {
       log.info("Scheduler Error: " + cause);
    }

    public void schedulerInStandbyMode() {
        try {
           log.info("Scheduler put in standby mode: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }

    public void schedulerStarted() {
        try {
           log.info("Scheduler started: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }

    public void schedulerShutdown() {
        try {
           log.info("Scheduler shutdown: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }

    public void schedulerShuttingdown() {
        try {
           log.info("Scheduler shutting down: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }

    public void schedulingDataCleared() {
        try {
           log.info("Scheduler data cleared: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }

    public void schedulerStarting() {
        try {
           log.info("Scheduler starting: "
                    + scheduler.getSchedulerName());
        } catch (SchedulerException e) {
           log.info("Error getting scheduler name" + e);
        }
    }
}
