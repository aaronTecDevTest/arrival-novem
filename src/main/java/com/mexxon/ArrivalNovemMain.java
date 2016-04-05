package com.mexxon;

import com.mexxon.utilities.SystemPreferences;
import com.mexxon.windows.view.ViewArrivalMainApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: main
 */

public class ArrivalNovemMain {
    private static final Logger log = LogManager.getLogger(ArrivalNovemMain.class);
    public ViewArrivalMainApp mainView;

    public static void main(String[] args) {
        SystemPreferences.getInstance();
        ArrivalNovemMain app = new ArrivalNovemMain();
        app.runLogIn();
    }

    public void runLogIn() {
        log.info("Start -- Mexxon - ImportExportTool");
        mainView = new ViewArrivalMainApp();
        mainView.run();
        log.info("Stop -- Mexxon - ImportExportTool");
    }
}