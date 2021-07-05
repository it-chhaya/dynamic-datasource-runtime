package com.data.openapi.settings;

import javax.sql.DataSource;

import com.data.openapi.config.DbRoutingDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

@Component
public class DbSwitcher {

    private AbstractRoutingDataSource routingDataSource;

    @Autowired
    public void setRoutingDataSource(AbstractRoutingDataSource routingDataSource) {
        this.routingDataSource = routingDataSource;
    }

    public void applySettings(DbSettings settings) {

        if (routingDataSource instanceof DbRoutingDataSource) {

            DataSource dataSource = DataSourceBuilder
                .create()
                .username(settings.getUsername())
                .password(settings.getPassword())
                .url(settings.getUrl())
                .driverClassName(settings.getDriverClassName())
                .build();

            DbRoutingDataSource rds = (DbRoutingDataSource) routingDataSource;

            rds.addDataSource(DbRoutingDataSource.NEW, dataSource);
            rds.setKey(DbRoutingDataSource.NEW);

        }

    }
    
}
