package com.mexxon.scheduler;

import com.mexxon.windows.model.DBJobConfigEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.todayAt;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 21.04.2016.
 * @since: 1.0
 * Package: com.mexxon.scheduler
 */

public class JobTrigger {
    private static final Logger log = LogManager.getLogger(JobTrigger.class);

    public JobTrigger() {
    }

    public static void main(String[] args) {
        System.out.println(getDateOf("2016-04-12 12:30:30"));
        System.out.println(getToDayAT("2016-04-12 12:36:36"));
    }

    public static Trigger getEveryMin(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startNow()
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();
        return trigger;
    }

    public static Trigger getEveryHour(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startNow()
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .build();
        return trigger;
    }

    public static Trigger getDaily(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startAt(getToDayAT(jobConfig.getStartTime()))
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInDays(1))
                .build();
        return trigger;
    }

    public static Trigger getWeekly(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startAt(getToDayAT(jobConfig.getStartTime()))
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInWeeks(1))
                .build();
        return trigger;
    }

    public static Trigger getMonthly(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startAt(getToDayAT(jobConfig.getStartTime()))
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInMonths(1))
                .build();
        return trigger;
    }

    public static Trigger getYearly(DBJobConfigEntity jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJobID()), jobConfig.getJobTyp())
                .startAt(getToDayAT(jobConfig.getStartTime()))
                .endAt(getDateOf(jobConfig.getEndTime()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInYears(1))
                .build();
        return trigger;
    }

    private static Date getDateOf(String date){
       // 2016-04-12 12:30:30 ("yyyy-mmm-dd hh:mm:ss");
        String []date1 = date.split(" ");

        String datum = date1[0];
        String time = date1[1];

        String []spitDatum = datum.split("-");
        String []spitTime = time.split(":");

        int yyyy = Integer.valueOf(spitDatum[0]);
        int mmm = Integer.valueOf(spitDatum[1]);
        int dd = Integer.valueOf(spitDatum[2]);

        int hh = Integer.valueOf(spitTime[0]);
        int mm = Integer.valueOf(spitTime[1]);
        int ss = Integer.valueOf(spitTime[2]);

        return dateOf(hh,mm,ss,dd,mmm,yyyy);
    }

    private static  Date getToDayAT(String date){
        // 2016-04-12 12:30:30 ("yyyy-mmm-dd hh:mm:ss");
        String []date1 = date.split(" ");

        String datum = date1[0];
        String time = date1[1];

        String []spitDatum = datum.split("-");
        String []spitTime = time.split(":");

        int dd = Integer.valueOf(spitDatum[0]);
        int mmm = Integer.valueOf(spitDatum[1]);
        int yyyy = Integer.valueOf(spitDatum[2]);

        int hh = Integer.valueOf(spitTime[0]);
        int mm = Integer.valueOf(spitTime[1]);
        int ss = Integer.valueOf(spitTime[2]);

        return todayAt(hh,mm,ss);
    }
}
