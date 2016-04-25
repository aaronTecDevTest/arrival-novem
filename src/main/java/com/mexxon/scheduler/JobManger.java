package com.mexxon.scheduler;

import com.mexxon.process.*;
import com.mexxon.utilities.WindowsDialogs;
import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.controller
 */

/**
 * For more Info: "https://examples.javacodegeeks.com/enterprise-java/quartz/quartz-scheduler-tutorial/
 *  Cron Triggers
 *  CronTrigger instances are built using TriggerBuilder and another helper class called CronScheduleBuilder which we
 *  can use to set the CronTrigger-specific properties. Cron-Expressions are used to
 *  configure instances of CronTrigger. Cron-Expressions are strings that are actually made up of seven sub-expressions,
 *  that describe individual details of the schedule. These sub-expression are
 *
 *  separated with white-space, and represent:
 *  1 Seconds
 *  2 Minutes
 *  3 Hours
 *  4 Day-of-Month
 *  5 Month
 *  6 Day-of-Week
 *  7 Year (optional field)
 *
 *  Example would be 0 15 10 * * ? * – Fire at 10:15am every day. You can also use some special characters, listed few important ones below:
 *  * every minute if * is placed in minute field
 *  ? useful when you need to specify something in one of the two fields in which the character is allowed, but not the other.
 *  – used to specify ranges.
 *  , to specify additional values
 *  / used to specify increments. For example, “0/15” in the seconds field means “the seconds 0, 15, 30, and 45″.
 *  In the below Example of Crone trigger, the trigger is setup to fire after a min from the current date time."
 */

public class JobManger {
    private static final Logger log = LogManager.getLogger(JobManger.class);
    private IFImportExport ifImportExport;
    private String simpleName;
    private String className;
    private JobBuilder jobBuilder;
    private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private Scheduler scheduler;
    private Map <String,Scheduler> schedulerMap;


    /**
     * Standard Constructor
     **/
    public JobManger() throws SchedulerException {
        this.schedulerFactory = new StdSchedulerFactory();
        this.scheduler = schedulerFactory.getScheduler();
        this.scheduler.getListenerManager().addSchedulerListener(new SchedulerListener(scheduler));
        this.schedulerMap = new HashMap();
    }

    /**
     * General Constructor
     **/
    public JobManger(IFImportExport ifImportExport, String simpleName, String className, JobBuilder jobBuilder) throws SchedulerException {
        this.ifImportExport = ifImportExport;
        this.simpleName = simpleName;
        this.className = className;
        this.jobBuilder = jobBuilder;
    }

    public static void main(String[] args) {
            DBJobConfigEntity confi = new DBJobConfigEntity((long) 3,
                "Beschreibung kklsdf",
                "import",
                "ttto",
                "tt",
                "dddd",
                "fff",
                "df",
                "dfd",
                "sddf",
                ";",
                "ideal");

            CSVImportSQLProcess csvImportSQLProcess = new CSVImportSQLProcess();
            csvImportSQLProcess.setDBJobConfigTable(confi);
            try {
                JobManger jobManger = new JobManger();
                jobManger.runJob(confi);
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
    }

    private void jobScheduler(IFImportExport ifImportExport, String simpleName, String className) throws SchedulerException, InterruptedException {
        String group = ifImportExport.getJobConfig().getJob_typ();
        String jobID = String.valueOf(ifImportExport.getJobConfig().getJob_id());

        //Define the job and tie it to our the class
        jobBuilder = ifImportExport.getJobBuilder();
        JobDataMap data = new JobDataMap();
        data.put("csvImportExport", ifImportExport);

        //Define job details
        JobDetail jobDetail = jobBuilder.usingJobData(simpleName, className)
                .usingJobData(data)
                .withIdentity(jobID, group)
                .build();

        //Trigger the job to run now, and then every 40 seconds
        Trigger trigger = getTrigger(ifImportExport.getJobConfig());

        //Tell quartz to schedule the job using our trigger (run the job)
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void runJob(DBJobConfigEntity jobConfig){
        EMProcessTyp emProcessTyp = EMProcessTyp.formString(jobConfig.getJob_typ());
        switch (emProcessTyp) {
            case EXPORT_MEXXON_CSV:{
                try {
                    CSVExportMexxonProcess job = new CSVExportMexxonProcess();
                    job.setDBJobConfigTable(jobConfig);
                    jobScheduler(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
            case IMPORT:{
                try {
                    CSVImportProcess job = new CSVImportProcess();
                    job.setDBJobConfigTable(jobConfig);
                    jobScheduler(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
            case EXPORT:{
                try {
                    CSVExportProcess job = new CSVExportProcess();
                    job.setDBJobConfigTable(jobConfig);
                    jobScheduler(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
            case IMPORT_SQL:{
                try {
                    CSVImportSQLProcess job = new CSVImportSQLProcess();
                    job.setDBJobConfigTable(jobConfig);
                    jobScheduler(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
            case EXPORT_SQL:{
                try {
                    CSVExportSQLProcess job = new CSVExportSQLProcess();
                    job.setDBJobConfigTable(jobConfig);
                    jobScheduler(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
                break;
            }
            default: new WindowsDialogs().jobStopResetDialog();
        }
    }

    public  void pauseJob(DBJobConfigEntity jobConfig){
        try {
            String key  = jobConfig.getJob_typ() + "."+String.valueOf(jobConfig.getJob_id());
            scheduler.resumeJob(JobKey.jobKey("1"));
        } catch (SchedulerException e) {
            log.error("Pause scheduler fail:" + e.getMessage());
        }

        /*
// Define a new Trigger
Trigger trigger = newTrigger()
    .withIdentity("newTrigger", "group1")
    .startNow()
    .build();

// tell the scheduler to remove the old trigger with the given key, and put the new one in its place
sched.rescheduleJob(triggerKey("oldTrigger", "group1"), trigger);*/
    }

    public void stopJob(DBJobConfigEntity jobConfig){

        try {
            String key  = jobConfig.getJob_typ() + "."+String.valueOf(jobConfig.getJob_id());
            //scheduler.interrupt("1.import");
            scheduler.interrupt(JobKey.jobKey("1","import"));
        } catch (SchedulerException e) {
            log.error("Stop scheduler fail:" + e.getMessage());
        }
    }

    public boolean isJobRunning(DBJobConfigEntity jobConfig){
        boolean isJobRunning = false;
        try {
            String key =String.valueOf(jobConfig.getJob_id());
            return schedulerFactory.getScheduler(key).checkExists(new JobKey(key));
        } catch (SchedulerException e) {
            log.error("Get scheduler status fail:" + e.getMessage());
        }
        return isJobRunning;
    }

    public void startScheduler(){
        //Start the scheduler
        try {
            scheduler.start();
        } catch (SchedulerException e) {
           log.error("Start scheduler fail:" + e.getMessage());
        }
    }

    public void stopScheduler(){
        try {
            //End the scheduler
            log.info("All triggers executed. Shutdown scheduler");
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error("Stop scheduler fail:" + e.getMessage());
        }
    }

    public  static Trigger getTrigger(DBJobConfigEntity jobConfig){
        String scheduler = jobConfig.getScheduler();

        switch (EMSchaduler.fromString(scheduler)){
            case EVERY_MIN:
                return JobTrigger.getEveryMin(jobConfig);
            case EVERY_HOUR:
                return JobTrigger.getEveryHour(jobConfig);
            case DAILY:
                return JobTrigger.getDaily(jobConfig);
            case WEEKLY:
                return JobTrigger.getWeekly(jobConfig);
            case MONTHLY:
                return JobTrigger.getMonthly(jobConfig);
            case YEARLY:
                return JobTrigger.getYearly(jobConfig);
            default:
                return null;
        }
    }

    public IFImportExport getIfImportExport() {
        return ifImportExport;
    }

    public void setIfImportExport(IFImportExport ifImportExport) {
        this.ifImportExport = ifImportExport;
    }
}