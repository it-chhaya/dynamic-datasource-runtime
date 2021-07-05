package com.data.openapi.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DbRoutingDataSource extends AbstractRoutingDataSource {

    public static final int DEFAULT = 0;
    public static final int NEW = 1;

    private volatile int key = DEFAULT;

    public void setKey(int key) {
        this.key = key;
    }

    private Map<Object, Object> datasources = new HashMap<>();

    public DbRoutingDataSource() {
        setTargetDataSources(datasources);
    }

    public void addDataSource(int key, DataSource dataSource) {
        datasources.put(key, dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return key;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        return (DataSource) datasources.get(key);
    }
    
}
