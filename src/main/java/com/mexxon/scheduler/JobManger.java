package com.mexxon.scheduler;

import com.mexxon.process.*;
import com.mexxon.utilities.WindowsDialogs;
import com.mexxon.windows.model.DBJobConfigTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


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


    /**
     * Standard Constructor
     **/
    public JobManger() throws SchedulerException {
        this.schedulerFactory = new StdSchedulerFactory();
        this.scheduler = schedulerFactory.getScheduler();
        this.scheduler.getListenerManager().addSchedulerListener(new SchedulerListener(scheduler));
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
        DBJobConfigTable confi = new DBJobConfigTable();

      CSVImportSQLProcess csvImportSQLProcess = new CSVImportSQLProcess(confi);
        try {
            JobManger schedulerController = new JobManger((IFImportExport) csvImportSQLProcess,
                csvImportSQLProcess.getClass().getSimpleName(),
                csvImportSQLProcess.getClass().getName(),
                csvImportSQLProcess.getJobBuilder());
            schedulerController.runJob(confi);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void iniJob(IFImportExport ifImportExport, String simpleName, String className) throws SchedulerException, InterruptedException {
        //Start the scheduler
        scheduler.start();

        // Define the job and tie it to our HelloJob class
        //JobBuilder jobBuilder = JobBuilder.newJob(JobExecution.class);
        jobBuilder = ifImportExport.getJobBuilder();
        JobDataMap data = new JobDataMap();
        data.put("ifImportExport", ifImportExport);

        JobDetail jobDetail = jobBuilder.usingJobData(simpleName, className)
                .usingJobData(data)
                //.withIdentity(String.valueOf(ifImportExport.getJobConfig().getJob_id()), "group1")
                .withIdentity(String.valueOf(ifImportExport.getJobConfig().getJob_id()), ifImportExport.getJobConfig().getJob_typ())
                .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                //.startAt(DateBuilder.todayAt(13,32,00))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(4))
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 " + "(min + 1)" + " " + "hour" + " * * ? *"))
                .build();

        // Tell quartz to schedule the job using our trigger
        scheduler.scheduleJob(jobDetail, trigger);

        //End the scheduler
        /*
        log.info("All triggers executed. Shutdown scheduler");
        scheduler.shutdown();*/
    }

    public void runJob(DBJobConfigTable jobConfig){

        switch (EMProcessTyp.formString(jobConfig.getJob_typ())) {
            case EXPORT_MEXXON_CSV:{
                try {
                    CSVExportMexxonProcess job = new CSVExportMexxonProcess(jobConfig);
                    iniJob(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            case IMPORT:{
                try {
                    CSVImportProcess job = new CSVImportProcess(jobConfig);
                    iniJob(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            case EXPORT:{
                try {
                    CSVExportProcess job = new CSVExportProcess(jobConfig);
                    iniJob(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            case IMPORT_SQL:{
                try {
                    CSVImportSQLProcess job = new CSVImportSQLProcess(jobConfig);
                    iniJob(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            case EXPORT_SQL:{
                try {
                    CSVExportSQLProcess job = new CSVExportSQLProcess(jobConfig);
                    iniJob(job,
                            job.getClass().getSimpleName(),
                            job.getClass().getName());
                } catch (SchedulerException e) {
                    log.error(e.getMessage());
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
            default: new WindowsDialogs().jobStopResetDialog();
        }
    }

    public  void resetJob(DBJobConfigTable jobConfig){
        try {
            schedulerFactory.getScheduler(String.valueOf(jobConfig.getJob_id())).shutdown();
            schedulerFactory.getScheduler(String.valueOf(jobConfig.getJob_id())).start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void stopJob(DBJobConfigTable jobConfig){
        try {
            schedulerFactory.getScheduler(String.valueOf(jobConfig.getJob_id())).shutdown();
            schedulerFactory.getScheduler(String.valueOf(jobConfig.getJob_id())).clear();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean isJobRunning(DBJobConfigTable jobConfig){
        boolean isJobRunning = false;

        return isJobRunning;
    }

    public IFImportExport getIfImportExport() {
        return ifImportExport;
    }

    public void setIfImportExport(IFImportExport ifImportExport) {
        this.ifImportExport = ifImportExport;
    }
}
