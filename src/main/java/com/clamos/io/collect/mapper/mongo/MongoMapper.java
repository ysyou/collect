package com.clamos.io.collect.mapper.mongo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MongoMapper {
    List<Map> getTables();
}
