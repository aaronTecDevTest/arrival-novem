package com.mexxon.scheduler;

import com.mexxon.windows.model.DBJobConfigTable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import java.util.Date;

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

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

    public static Trigger getEveryMin(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startNow()
                .endAt(getDateOf(jobConfig.getEnd_time()))
                .withSchedule(simpleSchedule()
                        .withIntervalInMinutes(1)
                        .repeatForever())
                .build();
        return trigger;
    }

    public static Trigger getEveryHour(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startNow()
                .endAt(getDateOf(jobConfig.getEnd_time()))
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(1)
                        .repeatForever())
                .build();
        return trigger;
    }

    public static Trigger getDaily(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startAt(getToDayAT(jobConfig.getStart_time()))
                .endAt(getDateOf(jobConfig.getEnd_time()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInDays(1))
                .build();
        return trigger;
    }

    public static Trigger getWeekly(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startAt(getToDayAT(jobConfig.getStart_time()))
                .endAt(getDateOf(jobConfig.getEnd_time()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInWeeks(1))
                .build();
        return trigger;
    }

    public static Trigger getMonthly(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startAt(getToDayAT(jobConfig.getStart_time()))
                .endAt(getDateOf(jobConfig.getEnd_time()))
                .withSchedule(calendarIntervalSchedule()
                        .withIntervalInMonths(1))
                .build();
        return trigger;
    }

    public static Trigger getYearly(DBJobConfigTable jobConfig){

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.valueOf(jobConfig.getJob_id()), jobConfig.getJob_typ())
                .startAt(getToDayAT(jobConfig.getStart_time()))
                .endAt(getDateOf(jobConfig.getEnd_time()))
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

    private static   Date getToDayAT(String date){
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
