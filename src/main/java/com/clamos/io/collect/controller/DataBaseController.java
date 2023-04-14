package com.clamos.io.collect.controller;

import com.clamos.io.collect.config.DataSourceConfig;
import com.clamos.io.collect.dto.DataSourceDTO;
import com.clamos.io.collect.mapper.mysql.MysqlMapper;
import com.clamos.io.collect.mapper.postgresql.PostgresqlMapper;
import com.clamos.io.collect.service.CommonService;
import com.clamos.io.collect.service.DataBaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
public class DataBaseController {

    final DataSourceConfig dataSourceConfig;
    final DataBaseService dataBaseService;
    final ObjectMapper objectMapper;
    final Map<String, DataSource> dataSourceMap = new HashMap<>();

    @PostMapping
    public Map<String, Object> connection(@RequestBody DataSourceDTO dataSourceDTO) throws Exception {
        DataSource dataSource;
        if (ObjectUtils.isEmpty(dataSourceMap.get(dataSourceDTO.getUrl()))) {
            dataSource = dataSourceConfig.createDataSource(dataSourceDTO);
        }else{
            dataSource = dataSourceMap.get(dataSourceDTO.getUrl());
        }
        Connection connection = dataSource.getConnection();
        if (!connection.isClosed()) {
            dataSourceMap.put(dataSourceDTO.getUrl(), dataSource);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("connection",!connection.isClosed());
        data.put("dataSource", dataSourceDTO);
        return data;
    }

    @PostMapping("/object")
    public Map<String,List<Map<String,Object>>> getObject(@RequestBody DataSourceDTO dataSourceDTO) throws Exception{
        DataSource dataSource = dataSourceMap.get(dataSourceDTO.getUrl());
        dataSourceDTO.setDataSource(dataSource);
        dataSourceConfig.addDataSource(dataSourceDTO);
        Map<String,List<Map<String,Object>>> schemas = dataBaseService.getSchemas(dataSourceDTO);
        return schemas;
    }

    @PutMapping
    public void change(){

    }
    @DeleteMapping
    public void delete(){

    }





}
