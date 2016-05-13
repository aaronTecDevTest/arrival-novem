package com.mexxon.windows.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016
 * @since: 1.0
 * Package: com.mexxon.model
 */

/**
 * ClassModel for Config and Process Status
 ***********************************************************************************************************************
 *
 *
 */

public class DBJobConfigEntity {
    private static final Logger log = LogManager.getLogger(DBJobConfigEntity.class);

    private SimpleLongProperty jobID;
    private SimpleStringProperty jobTyp;
    private SimpleStringProperty jobDescription;
    private SimpleStringProperty table;
    private SimpleStringProperty schema;
    private SimpleStringProperty startTime;
    private SimpleStringProperty endTime;
    private SimpleStringProperty scheduler;
    private SimpleStringProperty interval;
    private SimpleStringProperty fileSource;
    private SimpleStringProperty fileExtension;
    private SimpleStringProperty separator;
    private SimpleStringProperty encoding;
    private SimpleStringProperty email;
    private SimpleStringProperty hasHeader;
    private SimpleStringProperty type;
    private SimpleStringProperty partner;
    private SimpleStringProperty created;
    private SimpleStringProperty lastModified;
    private SimpleStringProperty isDeleted;
    private SimpleStringProperty userName;
    private SimpleLongProperty status;

    private ArrayList <DBColumnConfigEntity> columnConfigEntities;

    public DBJobConfigEntity() {
        this.jobID = new SimpleLongProperty();
        this.jobTyp = new SimpleStringProperty();
        this.jobDescription = new SimpleStringProperty();
        this.table = new SimpleStringProperty();
        this.schema = new SimpleStringProperty();
        this.startTime = new SimpleStringProperty();
        this.endTime = new SimpleStringProperty();
        this.scheduler = new SimpleStringProperty();
        this.interval = new SimpleStringProperty();
        this.separator = new SimpleStringProperty();
        this.fileSource = new SimpleStringProperty();
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

    public DBJobConfigEntity(long jobID, String jobTyp, String jobDescription, String table, String schema,
                             String startTime, String endTime, String scheduler, String interval,String fileSource,String fileExtension,
                             String separator, String encoding, String email, String hasHeader, String type,
                             String partner, String created, String lastModified, String isDeleted, String userName, long status) {

        this.jobID = new SimpleLongProperty(jobID);
        this.jobTyp = new SimpleStringProperty(jobTyp);
        this.jobDescription = new SimpleStringProperty(jobDescription);
        this.table = new SimpleStringProperty(table);
        this.schema = new SimpleStringProperty(schema);
        this.startTime = new SimpleStringProperty(startTime);
        this.endTime = new SimpleStringProperty(endTime);
        this.scheduler = new SimpleStringProperty(scheduler);
        this.interval = new SimpleStringProperty(interval);
        this.separator = new SimpleStringProperty(separator);
        this.fileSource = new SimpleStringProperty(fileSource);
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

    public long getJobID() {
        return jobID.get();
    }

    public void setJobID(long jobID) {
        this.jobID.set(jobID);
    }

    public SimpleLongProperty jobIDProperty() {
        return jobID;
    }

    public String getJobTyp() {
        return jobTyp.get();
    }

    public void setJobTyp(String jobTyp) {
        this.jobTyp.set(jobTyp);
    }

    public SimpleStringProperty jobTypProperty() {
        return jobTyp;
    }

    public String getJobDescription() {
        return jobDescription.get();
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription.set(jobDescription);
    }

    public SimpleStringProperty jobDescriptionProperty() {
        return jobDescription;
    }

    public String getTable() {
        return table.get();
    }

    public void setTable(String table) {
        this.table.set(table);
    }

    public SimpleStringProperty tableProperty() {
        return table;
    }

    public String getSchema() {
        return schema.get();
    }

    public void setSchema(String schema) {
        this.schema.set(schema);
    }

    public SimpleStringProperty schemaProperty() {
        return schema;
    }

    public String getStartTime() {
        return startTime.get();
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public SimpleStringProperty startTimeProperty() {
        return startTime;
    }

    public String getEndTime() {
        return endTime.get();
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }

    public SimpleStringProperty endTimeProperty() {
        return endTime;
    }

    public String getScheduler() {
        return scheduler.get();
    }

    public void setScheduler(String scheduler) {
        this.scheduler.set(scheduler);
    }

    public SimpleStringProperty schedulerProperty() {
        return scheduler;
    }

    public String getInterval() {
        return interval.get();
    }

    public void setInterval(String interval) {
        this.interval.set(interval);
    }

    public SimpleStringProperty intervalProperty() {
        return interval;
    }

    public String getFileSource() {
        return fileSource.get();
    }

    public SimpleStringProperty fileSourceProperty() {
        return fileSource;
    }

    public void setFileSource(String fileSource) {
        this.fileSource.set(fileSource);
    }

    public String getFileExtension() {
        return fileExtension.get();
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension.set(fileExtension);
    }

    public SimpleStringProperty fileExtensionProperty() {
        return fileExtension;
    }

    public String getSeparator() {
        return separator.get();
    }

    public void setSeparator(String separator) {
        this.separator.set(separator);
    }

    public SimpleStringProperty separatorProperty() {
        return separator;
    }

    public String getEncoding() {
        return encoding.get();
    }

    public void setEncoding(String encoding) {
        this.encoding.set(encoding);
    }

    public SimpleStringProperty encodingProperty() {
        return encoding;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getHasHeader() {
        return hasHeader.get();
    }

    public void setHasHeader(String hasHeader) {
        this.hasHeader.set(hasHeader);
    }

    public SimpleStringProperty hasHeaderProperty() {
        return hasHeader;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getPartner() {
        return partner.get();
    }

    public void setPartner(String partner) {
        this.partner.set(partner);
    }

    public SimpleStringProperty partnerProperty() {
        return partner;
    }

    public String getCreated() {
        return created.get();
    }

    public void setCreated(String created) {
        this.created.set(created);
    }

    public SimpleStringProperty createdProperty() {
        return created;
    }

    public String getLastModified() {
        return lastModified.get();
    }

    public void setLastModified(String lastModified) {
        this.lastModified.set(lastModified);
    }

    public SimpleStringProperty lastModifiedProperty() {
        return lastModified;
    }

    public String getIsDeleted() {
        return isDeleted.get();
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted.set(isDeleted);
    }

    public SimpleStringProperty isDeletedProperty() {
        return isDeleted;
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
    }

    public long getStatus() {
        return status.get();
    }

    public void setStatus(long status) {
        this.status.set(status);
    }

    public SimpleLongProperty statusProperty() {
        return status;
    }

    public ArrayList<DBColumnConfigEntity> getColumnConfigEntities() {
        return columnConfigEntities;
    }

    public void setColumnConfigEntities(ArrayList<DBColumnConfigEntity> columnConfigEntities) {
        this.columnConfigEntities = columnConfigEntities;
    }

    @Override
    public String toString() {
        return "DBJobConfigEntity{" +
                "jobID=" + jobID +
                ", jobTyp=" + jobTyp +
                ", jobDescription=" + jobDescription +
                ", table=" + table +
                ", schema=" + schema +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", scheduler=" + scheduler +
                ", interval=" + interval +
                ", fileSource=" + fileSource +
                ", fileExtension=" + fileExtension +
                ", separator=" + separator +
                ", encoding=" + encoding +
                ", email=" + email +
                ", hasHeader=" + hasHeader +
                ", type=" + type +
                ", partner=" + partner +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", isDeleted=" + isDeleted +
                ", userName=" + userName +
                ", status=" + status +
                '}';
    }
}