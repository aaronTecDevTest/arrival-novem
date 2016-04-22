package com.mexxon.windows.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
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
 CREATE TABLE `job_configuration` (
 `job_id` int NOT NULL AUTO_INCREMENT,
 `job_typ` char(100) NOT NULL,
 `job_description` char(100) NOT NULL,
 `from` char(100) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
 `to` char(100) NOT NULL COMMENT 'Kann eine TabelleName in der DB oder eine Dateinpath+Dateiname sein (C:....xxx.csv).',
 `start_time` datetime DEFAULT '2030-11-20 16:00:00',
 `end_time` datetime DEFAULT '2030-11-20 16:00:00',
 `scheduler` char(100) DEFAULT NULL COMMENT 'Eine Liste mit dem Werte: Daily, Weekly, 1st of the Month,15th of the Month',
 `expired_time` time DEFAULT NULL,
 `export_sql` text,
 `csv_separator` CHAR(1),
 PRIMARY KEY (`job_id`),
 KEY `job_config_job_id_index` (`job_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 ******************************************************************************************************************************************************************
 INSERT INTO importexport.job_configuration VALUES(NULL,'import','import to    the db','c:\dadf\dd','txn_tbl','2016-04-12 12:30:30.0','2016-04-12 12:30:30.0','daily','12:30:30','select *from txn_tbl',';');
 INSERT INTO importexport.job_configuration VALUES(NULL,'export','export frome the db','txn_tbl','c:\dadf\dd','2016-04-12 12:30:30.0','2016-04-12 12:30:30.0','daily','12:30:30','select *from txn_tbl',';');
 INSERT INTO importexport.job_configuration VALUES(NULL,'import_sql','import to    the db','c:\dadf\dd','txn_tbl','2016-04-12 12:30:30.0','2016-04-12 12:30:30.0','daily','12:30:30','select *from txn_tbl',';');
 INSERT INTO importexport.job_configuration VALUES(NULL,'export_sql','export frome the db','txn_tbl','c:\dadf\dd','2016-04-12 12:30:30.0','2016-04-12 12:30:30.0','daily','12:30:30','select *from txn_tbl',';');
 */

public class DBJobConfigEntity {
    private static final Logger log = LogManager.getLogger(DBJobConfigEntity.class);

    private SimpleLongProperty   job_id;
    private SimpleStringProperty job_typ;
    private SimpleStringProperty job_description;
    private SimpleStringProperty from;
    private SimpleStringProperty to;
    private SimpleStringProperty start_time;
    private SimpleStringProperty end_time;
    private SimpleStringProperty scheduler;
    private SimpleStringProperty expired_time;
    private SimpleStringProperty export_sql;
    private SimpleStringProperty csv_separator;
    private SimpleStringProperty job_status;

    public DBJobConfigEntity() {
        this((long) 0,"","","","","","","","","","","IDEAL");
    }

    public DBJobConfigEntity(Long job_id, String job_description, String job_typ, String from, String to, String start_time, String end_time, String scheduler, String expired_time, String export_sql, String csv_separator, String job_status) {
        this.job_id = new SimpleLongProperty(job_id);
        this.job_typ = new SimpleStringProperty(job_typ);
        this.job_description = new SimpleStringProperty(job_description);
        this.from = new SimpleStringProperty(from);
        this.to = new SimpleStringProperty(to);
        this.start_time = new SimpleStringProperty(start_time);
        this.end_time = new SimpleStringProperty(end_time);
        this.scheduler = new SimpleStringProperty(scheduler);
        this.expired_time = new SimpleStringProperty(expired_time);
        this.export_sql = new SimpleStringProperty(export_sql);
        this.csv_separator = new SimpleStringProperty(csv_separator);
        this.job_status = new SimpleStringProperty(job_status);
    }

    public long getJob_id() {
        return job_id.get();
    }

    public SimpleLongProperty job_idProperty() {
        return job_id;
    }

    public void setJob_id(long job_id) {
        this.job_id.set(job_id);
    }

    public String getJob_typ() {
        return job_typ.get();
    }

    public SimpleStringProperty job_typProperty() {
        return job_typ;
    }

    public void setJob_typ(String job_typ) {
        this.job_typ.set(job_typ);
    }

    public String getJob_description() {
        return job_description.get();
    }

    public SimpleStringProperty job_descriptionProperty() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description.set(job_description);
    }

    public String getFrom() {
        return from.get();
    }

    public SimpleStringProperty fromProperty() {
        return from;
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getTo() {
        return to.get();
    }

    public SimpleStringProperty toProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getStart_time() {
        return start_time.get();
    }

    public SimpleStringProperty start_timeProperty() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time.set(start_time);
    }

    public String getEnd_time() {
        return end_time.get();
    }

    public SimpleStringProperty end_timeProperty() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time.set(end_time);
    }

    public String getScheduler() {
        return scheduler.get();
    }

    public SimpleStringProperty schedulerProperty() {
        return scheduler;
    }

    public void setScheduler(String scheduler) {
        this.scheduler.set(scheduler);
    }

    public String getExpired_time() {
        return expired_time.get();
    }

    public SimpleStringProperty expired_timeProperty() {
        return expired_time;
    }

    public void setExpired_time(String expired_time) {
        this.expired_time.set(expired_time);
    }

    public String getExport_sql() {
        return export_sql.get();
    }

    public SimpleStringProperty export_sqlProperty() {
        return export_sql;
    }

    public void setExport_sql(String export_sql) {
        this.export_sql.set(export_sql);
    }

    public String getCsv_separator() {
        return csv_separator.get();
    }

    public SimpleStringProperty csv_separatorProperty() {
        return csv_separator;
    }

    public void setCsv_separator(String csv_separator) {
        this.csv_separator.set(csv_separator);
    }

    public String getJob_status() {
        return job_status.get();
    }

    public SimpleStringProperty job_statusProperty() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status.set(job_status);
    }
}