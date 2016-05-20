package com.mexxon.windows.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 06.05.2016
 * @since: 1.0
 * Package: com.mexxon.model
 */


@Entity
@Table(name = "column_transformation", schema = "importexport_config")
public class DBTransformationEntity {
    private static final Logger log = LogManager.getLogger(DBTransformationEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transformationID;
    private Long key;
    private String transformation;
    private String rule;
    private String created;
    private String lastModified;
    private String isDeleted;
    private String userName;
    private String status;

    public DBTransformationEntity() {
    }

    public DBTransformationEntity(Long transformationID, Long key, String transformation, String rule, String created,
                                  String lastModified, String isDeleted, String userName, String status) {
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

    public Long getTransformationID() {
        return transformationID;
    }

    public void setTransformationID(Long transformationID) {
        this.transformationID = transformationID;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DBTransformationEntity{" +
                "transformationID=" + transformationID +
                ", key=" + key +
                ", transformation='" + transformation + '\'' +
                ", rule='" + rule + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
