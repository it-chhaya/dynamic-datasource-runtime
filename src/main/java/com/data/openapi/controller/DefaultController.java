package com.data.openapi.controller;

import java.util.List;
import java.util.Map;

import com.data.openapi.service.DefaultService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    private Logger logger = LoggerFactory.getLogger(DefaultController.class);

    private DefaultService defaultService;

    @Autowired
    public void setDefaultService(DefaultService defaultService) {
        this.defaultService = defaultService;
    }
    
    @GetMapping("open-api/{tableName}")
    public List<Map<String, Object>> getAll(@PathVariable("tableName") String tableName) {
        return defaultService.getOpenApi(tableName);
    }

    @GetMapping("dynamic/{tableName}")
    public List<Map<String, Object>> getAllWithDynamicDb(@PathVariable("tableName") String tableName) {
        return defaultService.getOpenApi(tableName);
    }

    @GetMapping("open-api/{tableName}/{id}")
    public Map<String, Object> getById(@PathVariable("tableName") String tableName, @PathVariable("id") Integer id) {
        return defaultService.getRecordById(tableName, id);
    }

    @PostMapping("open-api/{tableName}")
    public Map<String, Object> addNewRecord(@PathVariable("tableName") String tableName, @RequestBody Map<String, Object> body) {

        logger.info("BODY = " + body);

        Map<String, Object> data = defaultService.insertNewRecord(tableName, body);

        return data;
    }



}
