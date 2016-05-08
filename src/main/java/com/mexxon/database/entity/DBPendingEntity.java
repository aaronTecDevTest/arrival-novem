package com.mexxon.database.entity;

import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 15.04.2016.
 * @since: 1.0
 * Package:  com.mexxon.database.entity
 */
public class DBPendingEntity implements IFMapping{
    private static final Logger log = LogManager.getLogger(DBPendingEntity.class);

    public DBPendingEntity() {
    }

    public HeaderColumnNameMappingStrategy<DBPendingEntity> getMappingStrategy() {
        return null;
    }

    @Override
    public String[] getConfigHeader(Map<String, Integer> columnConfig) {
        return new String[0];
    }

    @Override
    public String[] getDefaultHeader() {
        return new String[0];
    }
}
