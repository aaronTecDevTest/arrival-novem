package com.mexxon.windows.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.windows.controller
 */
public class FXMLArrivalMainController {
    private static final Logger log = LogManager.getLogger(FXMLArrivalMainController.class);


    /**
     * @param bundle For Internationalization
     */
    private ResourceBundle bundle;

    public void initialize(URL location, ResourceBundle resources) {
        //Ini BundleResources
         bundle = resources;
    }
}
