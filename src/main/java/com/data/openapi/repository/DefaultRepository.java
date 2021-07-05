package com.data.openapi.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultRepository {
    
    @SelectProvider(type = DefaultProvider.class, method = "buildDefaultSql")
    List<Map<String, Object>> select(@Param("tableName") String tableName);

    @SelectProvider(type = DefaultProvider.class, method = "buildDefaultSelectById")
    Map<String, Object> selectById(@Param("tableName") String tableName, @Param("id") Integer id);

    @InsertProvider(type = DefaultProvider.class, method = "buildInsertSql")
    void insert(@Param("tableName") String tableName, @Param("obj") Map<String, Object> obj);

    @UpdateProvider(type = DefaultProvider.class, method = "buildUpdateByIdSql")
    void update(@Param("tableName") String tableName, @Param("id") String id, @Param("newObj") Map<String, Object> newObj);

}
