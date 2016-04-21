package com.mexxon;

import com.mexxon.scheduler.JobManger;
import com.mexxon.utilities.Authentication;
import com.mexxon.utilities.SystemPreferences;
import com.mexxon.windows.view.ViewArrivalLogIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

    public static void main(String[] args) {
        SystemPreferences.getInstance();
        Authentication.getInstance();

        ImportExportMain app = new ImportExportMain();
        app.runLogIn();
    }

    public void runLogIn() {
        log.info(" -------------------------Start Mexxon ImportExportTool------------------------ ");
        {
            logInView = new ViewArrivalLogIn();
            logInView.run();
            JOB_MANGER.stopScheduler();
        }
        log.info(" --------------------------Stop Mexxon ImportExportTool------------------------- ");
    }
}