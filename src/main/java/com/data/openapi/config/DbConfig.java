package com.data.openapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Configuration
public class DbConfig {

    @Bean
    @Qualifier("default")
    public DataSource defaultDataSource() {
        return DataSourceBuilder
            .create()
            .username("postgres")
            .password("qwerqwer")
            .url("jdbc:postgresql://localhost:5432/postgres")
            .driverClassName("org.postgresql.Driver")
            .build();
    }

    @Bean
    @Primary
    @Scope("singleton")
    public AbstractRoutingDataSource routingDataSource(@Autowired @Qualifier("default") DataSource defaltDataSource) {
        DbRoutingDataSource dbRoutingDataSource = new DbRoutingDataSource();
        dbRoutingDataSource.addDataSource(DbRoutingDataSource.DEFAULT, defaltDataSource);
        dbRoutingDataSource.setDefaultTargetDataSource(defaltDataSource);
        return dbRoutingDataSource;
    }

}
