package com.mexxon;

import com.mexxon.scheduler.JobManger;
import com.mexxon.utilities.Authentication;
import com.mexxon.utilities.SystemPreferences;
import com.mexxon.windows.view.ViewArrivalLogIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;

import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: main
 */

public class ImportExportMain {
    private static final Logger log = LogManager.getLogger(ImportExportMain.class);
    /**
     * @param logInView  show the login view
     */
    public ViewArrivalLogIn logInView;
    /**
     * @param JOB_MANGER for all scheduler Job from DB
     */
    public static JobManger JOB_MANGER;

    /**
     * @param BUNDLE_CONFIG
     * @param BUNDLE_SQL
     * @param BUNDLE_MAIN
     * @param BUNDLE_LOGIN
     * @param BUNDLE_DIALOGS
     */
    public static ResourceBundle BUNDLE_CONFIG = SystemPreferences.getResourceBundle("arrivalConfig");
    public static ResourceBundle BUNDLE_SQL = SystemPreferences.getResourceBundle("arrivalSQL");
    public static ResourceBundle BUNDLE_MAIN = SystemPreferences.getResourceBundle("arrivalMain");
    public static ResourceBundle BUNDLE_LOGIN = SystemPreferences.getResourceBundle("arrivalLogIn");
    public static ResourceBundle BUNDLE_DIALOGS= SystemPreferences.getResourceBundle("arrivalDialogs");


    public static void main(String[] args) {
        SystemPreferences.getInstance();
        Authentication.getInstance();

        ImportExportMain app = new ImportExportMain();
        app.runLogIn();
    }

    public void runLogIn() {
        log.info(" -------------------------Start Mexxon ImportExportTool------------------------ ");
        {
            try {
                JOB_MANGER = new JobManger();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
            JOB_MANGER.startScheduler();
            logInView = new ViewArrivalLogIn();
            logInView.run();
            JOB_MANGER.stopScheduler();
        }
        log.info(" --------------------------Stop Mexxon ImportExportTool------------------------- ");
    }
}