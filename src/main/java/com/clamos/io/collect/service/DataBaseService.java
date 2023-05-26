package com.clamos.io.collect.service;

import com.clamos.io.collect.constants.Constant;
import com.clamos.io.collect.dto.DataSourceDTO;
import com.clamos.io.collect.mapper.mysql.MysqlMapper;
import com.clamos.io.collect.mapper.oracle.OracleMapper;
import com.clamos.io.collect.mapper.postgresql.PostgresqlMapper;
import com.clamos.io.collect.mapper.sqlserver.SqlServerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataBaseService {
    final OracleMapper oracleMapper;
    final MysqlMapper mysqlMapper;
    final PostgresqlMapper postgresqlMapper;
    final SqlServerMapper sqlServerMapper;

    public Map<String,List<Map<String,Object>>> getSchemas(DataSourceDTO dataSourceDTO) {
        List<Map> tabeles = new ArrayList<>();
        Map<String,List<Map<String,Object>>> schemas = new HashMap<>();
        switch (dataSourceDTO.getSourceName()){
            case Constant.MYSQL:
                tabeles = mysqlMapper.getTables(dataSourceDTO.getDbName());
                for (Map tabele : tabeles) {
                    String tablename = (String) tabele.get("tablename");
                    List<Map<String,Object>> columes = mysqlMapper.getColumes(tablename);
                    schemas.put(tablename, columes);
                }
                break;
            case Constant.POSTGRESQL:
                tabeles = postgresqlMapper.getTables();
                for (Map tabele : tabeles) {
                    String tablename = (String) tabele.get("tablename");
                    List<Map<String,Object>> columes = postgresqlMapper.getColumes(tablename);
                    schemas.put(tablename, columes);
                }
                break;
            case Constant.ORACLE:
                tabeles = oracleMapper.getTables();
                break;
            case Constant.SQLSERVER:
                tabeles = sqlServerMapper.getTables();
                break;
            case Constant.MONGO:
                /*몽고는 추후 */
                break;
        }
        return schemas;
    }
}
