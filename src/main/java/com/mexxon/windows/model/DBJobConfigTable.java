package com.mexxon.windows.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.model
 */

/**
 * ClassModel for Config and Process Status
 *
  CREATE TABLE `job_config` (
 `job_id` double NOT NULL AUTO_INCREMENT,
 `job_typ` char(1) NOT NULL,
 `from` char(1) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
 `to` char(1) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
 `start_time` datetime DEFAULT '2030-11-20 16:00:00',
 `end_time` datetime DEFAULT '2030-11-20 16:00:00',
 `scheduler` char(1) DEFAULT NULL COMMENT 'Eine Liste mit dem Werte: Daily, Weekly, 1st of the Month,15th of the Month',
 `expired_time` time DEFAULT NULL,
 `export_sql` char(1) DEFAULT NULL,
 PRIMARY KEY (`job_id`),
 KEY `job_config_job_id_index` (`job_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */

public class DBJobConfigTable {
    private static final Logger log = LogManager.getLogger(DBJobConfigTable.class);

    public DBJobConfigTable() {
    }


}
