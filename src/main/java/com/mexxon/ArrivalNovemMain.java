package com.mexxon;

import com.mexxon.notification.EMail;
import com.mexxon.scheduler.SchedulerController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: main
 */

public class ArrivalNovemMain {
    private static final Logger log = LogManager.getLogger(ArrivalNovemMain.class);

    public ArrivalNovemMain() {
    }

    public static void main(String[] args) {
        log.info("Main: ArrvalNovem is run!!");
        /*
        SchedulerController schedulerController = new SchedulerController();
        try {
            schedulerController.iniJob();
        } catch (InterruptedException e) {
            log.error("InterruptedException: " + e.getMessage());
        } catch (SchedulerException e) {
            log.error("SchedulerException: " + e.getMessage());
        }*/

        EMail eMail = new EMail();
        if(eMail.sendEmail())
            log.info("Email success");
        else
            log.info("Email fail");

    }
}