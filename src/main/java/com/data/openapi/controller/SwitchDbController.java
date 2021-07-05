package com.data.openapi.controller;

import com.data.openapi.settings.DbSettings;
import com.data.openapi.settings.DbSwitcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwitchDbController {

    private DbSwitcher dbSwitcher;

    @Autowired
    public void setDbSwitcher(DbSwitcher dbSwitcher) {
        this.dbSwitcher = dbSwitcher;
    }
    
    @GetMapping("/switch-newdb")
    public String switchNewDb() {

        DbSettings settings = new DbSettings();
        settings.setUsername("postgres");
        settings.setPassword("qwerqwer");
        settings.setUrl("jdbc:postgresql://localhost:5432/client");
        settings.setDriverClassName("org.postgresql.Driver");

        dbSwitcher.applySettings(settings);
        
        return "swtiched successfully";

    }

    @GetMapping("/switch-olddb")
    public String switchOldDb() {

        DbSettings settings = new DbSettings();
        settings.setUsername("postgres");
        settings.setPassword("qwerqwer");
        settings.setUrl("jdbc:postgresql://localhost:5432/postgres");
        settings.setDriverClassName("org.postgresql.Driver");

        dbSwitcher.applySettings(settings);
        
        return "swtiched successfully";

    }

}
