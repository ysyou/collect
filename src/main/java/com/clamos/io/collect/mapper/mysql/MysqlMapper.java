package com.clamos.io.collect.mapper.mysql;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MysqlMapper {
    List<Map> getTables(String dbName);
    List<Map<String,Object>> getColumes(String tablename);
}
