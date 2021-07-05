package com.data.openapi.service;

import java.util.List;
import java.util.Map;

import com.data.openapi.repository.DefaultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultService {
    
    private DefaultRepository defaultRepository;

    @Autowired
    public void setDefaultRepository(DefaultRepository defaultRepository) {
        this.defaultRepository = defaultRepository;
    }

    public List<Map<String, Object>> getOpenApi(String tableName) {
        return defaultRepository.select(tableName);
    }

    public Map<String, Object> getRecordById(String tableName, Integer id) {
        return defaultRepository.selectById(tableName, id);
    }

    public Map<String, Object> insertNewRecord(String tableName, Map<String, Object> obj) {
        defaultRepository.insert(tableName, obj);
        return obj;
    }


}
