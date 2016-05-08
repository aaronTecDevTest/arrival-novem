package com.mexxon.database.entity;


import java.util.Map;

/**
 * @author: Aaron Kutekidila
 * @version: 1.0
 * Created: 07.05.2016.
 * @since: 1.0
 * Package: com.mexxon.database.entity
 */
public interface IFMapping {
    String [] getDefaultHeader();
    String [] getConfigHeader(Map<String,Integer> columnConfig);
}
