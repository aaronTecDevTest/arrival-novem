package com.mexxon;

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
    public ViewArrivalLogIn logInView;

    public static void main(String[] args) {
        SystemPreferences.getInstance();
        Authentication.getInstance();

        ImportExportMain app = new ImportExportMain();
        app.runLogIn();
    }

    public void runLogIn() {
        log.info("Start ------------------------- Mexxon ------------------------ ImportExportTool");
        {
            logInView = new ViewArrivalLogIn();
            logInView.run();
        }
        log.info("Stop -------------------------- Mexxon ------------------------- ImportExportTool");
    }
}