package com.mexxon.windows.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 06.05.2016
 * @since: 1.0
 * Package: com.mexxon.model
 */

public class DBTransformationEntity {
    private static final Logger log = LogManager.getLogger(DBTransformationEntity.class);

    private SimpleLongProperty transformationID;
    private SimpleStringProperty key;
    private SimpleStringProperty transformation;
    private SimpleStringProperty rule;
    private SimpleStringProperty created;
    private SimpleStringProperty lastModified;
    private SimpleStringProperty isDeleted;
    private SimpleStringProperty userName;
    private SimpleStringProperty status;

    public DBTransformationEntity() {
    }

    public DBTransformationEntity(SimpleLongProperty transformationID, SimpleStringProperty key, SimpleStringProperty transformation,
                                  SimpleStringProperty rule, SimpleStringProperty created, SimpleStringProperty lastModified,
                                  SimpleStringProperty isDeleted, SimpleStringProperty userName, SimpleStringProperty status) {
        this.transformationID = transformationID;
        this.key = key;
        this.transformation = transformation;
        this.rule = rule;
        this.created = created;
        this.lastModified = lastModified;
        this.isDeleted = isDeleted;
        this.userName = userName;
        this.status = status;
    }

    public long getTransformationID() {
        return transformationID.get();
    }

    public void setTransformationID(long transformationID) {
        this.transformationID.set(transformationID);
    }

    public SimpleLongProperty transformationIDProperty() {
        return transformationID;
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

    public String getTransformation() {
        return transformation.get();
    }

    public void setTransformation(String transformation) {
        this.transformation.set(transformation);
    }

    public SimpleStringProperty transformationProperty() {
        return transformation;
    }

    public String getRule() {
        return rule.get();
    }

    public void setRule(String rule) {
        this.rule.set(rule);
    }

    public SimpleStringProperty ruleProperty() {
        return rule;
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

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    @Override
    public String toString() {
        return "DBTransformationEntity{" +
                "transformationID=" + transformationID +
                "key=" + key +
                ", transformation=" + transformation +
                ", rule=" + rule +
                ", created=" + created +
                ", lastModified=" + lastModified +
                ", isDeleted=" + isDeleted +
                ", userName=" + userName +
                ", status=" + status +
                '}';
    }
}
