package com.mexxon.windows.model;

import javafx.beans.property.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016
 * @since: 1.0
 * Package: com.mexxon.model
 */

/**
 *
 * ClassModel for Config and Process Status
 ***********************************************************************************************************************
 *
 */


@Entity
@Table(name = "job_configuration", schema = "importexport_config")
@Access(value = AccessType.PROPERTY)
public class DBJobConfigEntity {
    private static final Logger log = LogManager.getLogger(DBJobConfigEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "jobID")
    private final IntegerProperty jobID;

    @Column(name = "jobTyp")
    private final StringProperty jobTyp;
    @Column(name = "jobDescription")
    private final StringProperty jobDescription;
    @Column(name = "table")
    private final StringProperty table;
    @Column(name = "schema")
    private final StringProperty schema;
    @Column(name = "startTime")
    private final StringProperty startTime;
    @Column(name = "endTime")
    private final StringProperty endTime;
    @Column(name = "Scheduler")
    private final StringProperty scheduler;
    @Column(name = "interval")
    private final StringProperty interval;
    @Column(name = "filePath")
    private final StringProperty filePath;
    @Column(name = "fileExtension")
    private final StringProperty fileExtension;
    @Column(name = "separator")
    private final StringProperty separator;
    @Column(name = "encoding")
    private final StringProperty encoding;
    @Column(name = "email")
    private final StringProperty email;
    @Column(name = "hasHeader")
    private final StringProperty hasHeader;
    @Column(name = "type")
    private final StringProperty type;
    @Column(name = "partner")
    private final StringProperty partner;
    @Column(name = "created")
    private final StringProperty created;
    @Column(name = "lastModified")
    private final StringProperty lastModified;
    @Column(name = "deleted")
    private final StringProperty isDeleted;
    @Column(name = "userName")
    private final StringProperty userName;
    @Column(name = "status")
    private final LongProperty status;
//
//    private final ArrayList <DBColumnConfigEntity> columnConfigEntities;

    public DBJobConfigEntity() {
        this.jobID = new SimpleIntegerProperty();
        this.jobTyp = new SimpleStringProperty();
        this.jobDescription = new SimpleStringProperty();
        this.table = new SimpleStringProperty();
        this.schema = new SimpleStringProperty();
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();
        this.scheduler = new SimpleStringProperty();
        this.interval = new SimpleStringProperty();
        this.separator = new SimpleStringProperty();
        this.filePath = new SimpleStringProperty();
        this.fileExtension = new SimpleStringProperty();
        this.encoding = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.hasHeader = new SimpleStringProperty();
        this.type = new SimpleStringProperty();
        this.partner = new SimpleStringProperty();
        this.created = new SimpleStringProperty();
        this.lastModified = new SimpleStringProperty();
        this.isDeleted = new SimpleStringProperty();
        this.userName = new SimpleStringProperty();
        this.status = new SimpleLongProperty();
    }

    public DBJobConfigEntity(int jobID, String jobTyp, String jobDescription, String table, String schema,
                             String startTime, String endTime, String scheduler, String interval, String filePath, String fileExtension,
                             String separator, String encoding, String email, String hasHeader, String type,
                             String partner, String created, String lastModified, String isDeleted, String userName, long status) {

        this.jobID = new SimpleIntegerProperty(jobID);
        this.jobTyp = new SimpleStringProperty(jobTyp);
        this.jobDescription = new SimpleStringProperty(jobDescription);
        this.table = new SimpleStringProperty(table);
        this.schema = new SimpleStringProperty(schema);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.scheduler = new SimpleStringProperty(scheduler);
        this.interval = new SimpleStringProperty(interval);
        this.separator = new SimpleStringProperty(separator);
        this.filePath = new SimpleStringProperty(filePath);
        this.fileExtension = new SimpleStringProperty(fileExtension);
        this.encoding = new SimpleStringProperty(encoding);
        this.email = new SimpleStringProperty(email);
        this.hasHeader = new SimpleStringProperty(hasHeader);
        this.type = new SimpleStringProperty(type);
        this.partner = new SimpleStringProperty(partner);
        this.created = new SimpleStringProperty(created);
        this.lastModified = new SimpleStringProperty(lastModified);
        this.isDeleted = new SimpleStringProperty(isDeleted);
        this.userName = new SimpleStringProperty(userName);
        this.status = new SimpleLongProperty(status);
    }

    @Id
    @Access(value = AccessType.PROPERTY)
    public long getJobID() {
        return jobID.get();
    }

    @Id
    public void setJobID(int jobID) {
        this.jobID.set(jobID);
    }

    public IntegerProperty jobIDProperty() {
        return jobID;
    }


    @Access(value = AccessType.PROPERTY)
    public String getJobTyp() {
        return jobTyp.get();
    }

    public void setJobTyp(String jobTyp) {
        this.jobTyp.set(jobTyp);
    }

    public StringProperty jobTypProperty() {
        return jobTyp;
    }


    @Access(value = AccessType.PROPERTY)
    public String getJobDescription() {
        return jobDescription.get();
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription.set(jobDescription);
    }

    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }


    @Access(value = AccessType.PROPERTY)
    public String getTable() {
        return table.get();
    }

    public void setTable(String table) {
        this.table.set(table);
    }

    public StringProperty tableProperty() {
        return table;
    }


    @Access(value = AccessType.PROPERTY)
    public String getSchema() {
        return schema.get();
    }

    public void setSchema(String schema) {
        this.schema.set(schema);
    }

    public StringProperty schemaProperty() {
        return schema;
    }


    @Access(value = AccessType.PROPERTY)
    public String getStartTime() {
        return startTime.get();
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }


    @Access(value = AccessType.PROPERTY)
    public String getEndTime() {
        return endTime.get();
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public StringProperty endTimeProperty() {
        return endTime;
    }


    @Access(value = AccessType.PROPERTY)
    public String getScheduler() {
        return scheduler.get();
    }

    public void setScheduler(String scheduler) {
        this.scheduler.set(scheduler);
    }

    public StringProperty schedulerProperty() {
        return scheduler;
    }


    @Access(value = AccessType.PROPERTY)
    public String getInterval() {
        return interval.get();
    }

    public void setInterval(String interval) {
        this.interval.set(interval);
    }

    public StringProperty intervalProperty() {
        return interval;
    }

    @Access(value = AccessType.PROPERTY)
    public String getFilePath() {
        return filePath.get();
    }

    public void setFilePath(String filePath) {
        this.filePath.set(filePath);
    }

    public StringProperty filePathProperty() {
        return filePath;
    }


    @Access(value = AccessType.PROPERTY)
    public String getFileExtension() {
        return fileExtension.get();
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension.set(fileExtension);
    }

    public StringProperty fileExtensionProperty() {
        return fileExtension;
    }


    @Access(value = AccessType.PROPERTY)
    public String getSeparator() {
        return separator.get();
    }

    public void setSeparator(String separator) {
        this.separator.set(separator);
    }

    public StringProperty separatorProperty() {
        return separator;
    }


    public String getEncoding() {
        return encoding.get();
    }

    public void setEncoding(String encoding) {
        this.encoding.set(encoding);
    }

    public StringProperty encodingProperty() {
        return encoding;
    }


    @Access(value = AccessType.PROPERTY)
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }


    @Access(value = AccessType.PROPERTY)
    public String getHasHeader() {
        return hasHeader.get();
    }

    public void setHasHeader(String hasHeader) {
        this.hasHeader.set(hasHeader);
    }

    public StringProperty hasHeaderProperty() {
        return hasHeader;
    }


    @Access(value = AccessType.PROPERTY)
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }


    @Access(value = AccessType.PROPERTY)
    public String getPartner() {
        return partner.get();
    }

    public void setPartner(String partner) {
        this.partner.set(partner);
    }

    public StringProperty partnerProperty() {
        return partner;
    }


    @Access(value = AccessType.PROPERTY)
    public String getLastModified() {
        return lastModified.get();
    }

    public void setLastModified(String lastModified) {
        this.lastModified.set(lastModified);
    }

    public StringProperty lastModifiedProperty() {
        return lastModified;
    }


/*
    @Access(value = AccessType.PROPERTY)
    public String getIsDeleted() {
        return isDeleted.get();
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted.set(isDeleted);
    }

    public StringProperty isDeletedProperty() {
        return isDeleted;
    }*/


    @Access(value = AccessType.PROPERTY)
    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public StringProperty userNameProperty() {
        return userName;
    }


    @Access(value = AccessType.PROPERTY)
    public long getStatus() {
        return status.get();
    }

    public void setStatus(long status) {
        this.status.set(status);
    }

    public LongProperty statusProperty() {
        return status;
    }


    @Access(value = AccessType.PROPERTY)
    public String getCreated() {
        return created.get();
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public StringProperty createdProperty() {
        return created;
    }
}