package com.clamos.io.collect.mapper.oracle;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface OracleMapper {
    List<Map> getTables();
    List<Map> getColumes();
}
