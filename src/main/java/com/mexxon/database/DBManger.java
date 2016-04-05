package com.mexxon.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 31.03.2016.
 * @since: 1.0
 * Package: com.mexxon.database
 */

public class DBManger {
    private static final Logger log = LogManager.getLogger(DBManger.class);

    private ArrayList<String>  sqlTabelSeceltList = new ArrayList<String>();

    public DBManger() {
    }
}
