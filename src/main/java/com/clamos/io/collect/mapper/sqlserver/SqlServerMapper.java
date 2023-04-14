package com.clamos.io.collect.mapper.sqlserver;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SqlServerMapper {
    List<Map> getTables();
    List<Map> getColumes();
}
