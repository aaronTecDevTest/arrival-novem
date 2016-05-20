package com.mexxon.windows.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 05.04.2016.
 * @since: 1.0
 * Package: com.mexxon.model
 */


@Entity
@Table(name = "mexxon_mapping" , schema = "importexport_config")
public class DBMexxonMappingEntity {
    private static final Logger log = LogManager.getLogger(DBMexxonMappingEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mexxonMappingId;


    public DBMexxonMappingEntity() {
    }

    public DBMexxonMappingEntity(Long mexxonMappingId) {
        this.mexxonMappingId = mexxonMappingId;
    }

    public Long getMexxonMappingId() {
        return mexxonMappingId;
    }

    public void setMexxonMappingId(Long mexxonMappingId) {
        this.mexxonMappingId = mexxonMappingId;
    }

    @Override
    public String toString() {
        return "DBMexxonMappingEntity{" +
                "mexxonMappingId=" + mexxonMappingId +
                '}';
    }
}
