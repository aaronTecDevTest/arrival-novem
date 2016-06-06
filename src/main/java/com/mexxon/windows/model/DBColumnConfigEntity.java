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
@Table(name = "column_configuration", schema = "importexport_config")
public class DBColumnConfigEntity {
    private static final Logger log = LogManager.getLogger(DBColumnConfigEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long columnConfigID;
    private Long jobID;
    private Long key;
    private String column;
    private String orgColumn;
    private String length;
    private String created;
    private String lastModified;
    private String userName;
    private String isDeleted;
    private String status;

    private DBTransformationEntity transformationEntities;

    public DBColumnConfigEntity(Long columnConfigID, Long jobID, Long key, String column, String orgColumn, String length,
                                String created, String lastModified, String userName, String isDeleted, String status,
                                DBTransformationEntity transformationEntities) {
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
        this.transformationEntities = transformationEntities;
    }

    public Long getColumnConfigID() {
        return columnConfigID;
    }

    public void setColumnConfigID(Long columnConfigID) {
        this.columnConfigID = columnConfigID;
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOrgColumn() {
        return orgColumn;
    }

    public void setOrgColumn(String orgColumn) {
        this.orgColumn = orgColumn;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DBTransformationEntity getTransformationEntities() {
        return transformationEntities;
    }

    public void setTransformationEntities(DBTransformationEntity transformationEntities) {
        this.transformationEntities = transformationEntities;
    }

    @Override
    public String toString() {
        return "DBColumnConfigEntity{" +
                "columnConfigID=" + columnConfigID +
                ", jobID=" + jobID +
                ", key=" + key +
                ", column='" + column + '\'' +
                ", orgColumn='" + orgColumn + '\'' +
                ", length='" + length + '\'' +
                ", created='" + created + '\'' +
                ", lastModified='" + lastModified + '\'' +
                ", userName='" + userName + '\'' +
                ", isDeleted='" + isDeleted + '\'' +
                ", status='" + status + '\'' +
                ", transformationEntities=" + transformationEntities +
                '}';
    }
}
