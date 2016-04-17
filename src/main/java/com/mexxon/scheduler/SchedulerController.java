package com.mexxon.scheduler;

import com.mexxon.process.CSVImportSQLProcess;
import com.mexxon.process.IFImportExport;
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

public class SchedulerController  {
    private static final Logger log = LogManager.getLogger(SchedulerController.class);
    private IFImportExport ifImportExport;
    private String simpelName;
    private String className;

    /**
     * Standard Constructor
     **/
    public SchedulerController(IFImportExport ifImportExport, String simpleName, String className) {
        this.ifImportExport = ifImportExport;
        this.simpelName = simpleName;
        this.className = className;
    }

    public static void main(String[] args) {
        CSVImportSQLProcess csvImportSQLProcess = new CSVImportSQLProcess();
        SchedulerController schedulerController = new SchedulerController((IFImportExport) csvImportSQLProcess,
                csvImportSQLProcess.getClass().getSimpleName(),
                csvImportSQLProcess.getClass().getName());

        try {
            schedulerController.iniJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void iniJob() throws SchedulerException, InterruptedException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.getListenerManager().addSchedulerListener(new SchedulerListener(scheduler));

        //Start the scheduler
        scheduler.start();

        // Define the job and tie it to our HelloJob class
        JobBuilder jobBuilder = JobBuilder.newJob(JobExecution.class);
        JobDataMap data = new JobDataMap();
        data.put("ifImportExport", ifImportExport);

        JobDetail jobDetail = jobBuilder.usingJobData(simpelName, className)
                .usingJobData(data)
                .withIdentity(String.valueOf(ifImportExport.getJobConfig().getJob_id()), "group1")
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
        log.info("All triggers executed. Shutdown scheduler");
        scheduler.shutdown();
    }

    public IFImportExport getIfImportExport() {
        return ifImportExport;
    }

    public void setIfImportExport(IFImportExport ifImportExport) {
        this.ifImportExport = ifImportExport;
    }


}
