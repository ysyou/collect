package com.clamos.io.collect.mapper.postgresql;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostgresqlMapper {
    List<Map> getTables();
    List<Map<String,Object>> getColumes(String tablename);
}
