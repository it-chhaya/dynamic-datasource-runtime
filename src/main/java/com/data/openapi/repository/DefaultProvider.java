package com.data.openapi.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class DefaultProvider {
    
    public String buildDefaultSql(@Param("tableName") String tableName) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
        }}.toString();
    }

    public String buildDefaultSelectById(@Param("tableName") String tableName, @Param("id") Integer id) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildInsertSql(@Param("tableName") String tableName, @Param("obj") Map<String, Object> obj) {
        String sql = new SQL() {{
            INSERT_INTO(tableName);
            for (String key: obj.keySet()) {
                String value = obj.get(key).toString();
                VALUES(key, "'" + value + "'");
            }
        }}.toString();
        System.out.println("SQL = " + sql);
        return sql;
    }

    public String buildUpdateByIdSql() {
        return new SQL() {{
            
        }}.toString();
    }

}
