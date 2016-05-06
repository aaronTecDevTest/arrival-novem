package com.mexxon.windows.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 06.05.2016
 * @since: 1.0
 * Package: com.mexxon.model
 */

public class DBColumnConfigEntity {
    private static final Logger log = LogManager.getLogger(DBColumnConfigEntity.class);

    private SimpleLongProperty columnConfigID;
    private SimpleLongProperty jobID;
    private SimpleStringProperty key;
    private SimpleStringProperty column;
    private SimpleStringProperty orgColumn;
    private SimpleStringProperty length;
    private SimpleStringProperty created;
    private SimpleStringProperty lastModified;
    private SimpleStringProperty userName;
    private SimpleStringProperty isDeleted;
    private SimpleStringProperty status;

    private ArrayList<DBTransformationEntity> transformationEntities;

    public DBColumnConfigEntity() {
    }

    public DBColumnConfigEntity(SimpleLongProperty columnConfigID, SimpleLongProperty jobID, SimpleStringProperty key,
                                SimpleStringProperty column, SimpleStringProperty orgColumn, SimpleStringProperty length,
                                SimpleStringProperty created, SimpleStringProperty lastModified, SimpleStringProperty userName,
                                SimpleStringProperty isDeleted, SimpleStringProperty status) {
        this.columnConfigID = columnConfigID;
        this.jobID = jobID;
        this.key = key;
        this.column = column;
        this.orgColumn = orgColumn;
        this.length = length;
        this.created = created;
        this.lastModified = lastModified;
        this.userName = userName;
        this.isDeleted = isDeleted;
        this.status = status;
    }

    public long getColumnConfigID() {
        return columnConfigID.get();
    }

    public void setColumnConfigID(long columnConfigID) {
        this.columnConfigID.set(columnConfigID);
    }

    public SimpleLongProperty columnConfigIDProperty() {
        return columnConfigID;
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

    public String getKey() {
        return key.get();
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public SimpleStringProperty keyProperty() {
        return key;
    }

    public String getColumn() {
        return column.get();
    }

    public void setColumn(String column) {
        this.column.set(column);
    }

    public SimpleStringProperty columnProperty() {
        return column;
    }

    public String getOrgColumn() {
        return orgColumn.get();
    }

    public void setOrgColumn(String orgColumn) {
        this.orgColumn.set(orgColumn);
    }

    public SimpleStringProperty orgColumnProperty() {
        return orgColumn;
    }

    public String getLength() {
        return length.get();
    }

    public void setLength(String length) {
        this.length.set(length);
    }

    public SimpleStringProperty lengthProperty() {
        return length;
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

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public SimpleStringProperty userNameProperty() {
        return userName;
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

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public ArrayList<DBTransformationEntity> getTransformationEntities() {
        return transformationEntities;
    }

    public void setTransformationEntities(ArrayList<DBTransformationEntity> transformationEntities) {
        this.transformationEntities = transformationEntities;
    }

    @Override
    public String toString() {
        return "DBColumnConfigEntity{" +
                "columnConfigID=" + columnConfigID +
                ", jobID=" + jobID +
                ", key=" + key +
                ", column=" + column +
                ", orgColumn=" + orgColumn +
                ", length=" + length +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", userName=" + userName +
                ", isDeleted=" + isDeleted +
                ", status=" + status +
                '}';
    }
}
