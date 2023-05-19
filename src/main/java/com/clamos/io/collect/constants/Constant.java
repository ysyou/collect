package com.clamos.io.collect.constants;

public final class Constant {
    public static final String MYSQL_CONNECTOR_CLASS = "io.debezium.connector.mysql.MySqlConnector";
    public static final String POSTGRESQL_CONNECTOR_CLASS = "io.debezium.connector.postgresql.PostgresConnector";
    public static final String SQLSERVER_CONNECTOR_CLASS = "io.debezium.connector.sqlserver.SqlServerConnector";
    public static final String ORACLE_CONNECTOR_CLASS = "io.debezium.connector.oracle.OracleConnector";
    public static final String MONGO_CONNECTOR_CLASS = "io.debezium.connector.mongodb.MongoDbConnector";
    public static final String MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String POSTGRESQL_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    public static final String SQLSERVER_DRIVER_CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String ORACLE_DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    public static final String MONGO_DRIVER_CLASS_NAME = "";


    public static final String OFFSET_STORAGE_FILE_FILENAME = "offsets.dat";
    public static final String DATABASE_HISTORY_FILE_FILENAME = "dbhistory.dat";
    public static final Integer MYSQL_SERVER_ID = 10181;
    public static final Integer POSTGRESQL_SERVER_ID = 184055;
    public static final Integer SQLSERVER_SERVER_ID = 184056;
    public static final Integer MONGO_SERVER_ID = 184057;
    public static final Integer ORACLE_SERVER_ID = 184058;

    public static final String MYSQL = "mysql";
    public static final String POSTGRESQL = "postgresql";
    public static final String SQLSERVER = "sqlserver";
    public static final String MONGO = "mongo";
    public static final String ORACLE = "oracle";

}
