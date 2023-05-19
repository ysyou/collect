package com.clamos.io.collect.dto;

import lombok.Data;

import javax.sql.DataSource;

@Data
public class DataSourceDTO {
    String url;
    String host;
    String port;
    String dbName;
    String dbUser;
    String dbPassword;
    String dbType;
    String type;
    DataSource dataSource;
}
