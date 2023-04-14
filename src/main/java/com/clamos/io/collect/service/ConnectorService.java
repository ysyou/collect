package com.clamos.io.collect.service;

import com.clamos.io.collect.component.ConnectorListener;
import com.clamos.io.collect.constants.Constant;
import com.clamos.io.collect.dto.DataSourceDTO;
import io.debezium.config.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ConnectorService {
//    final ConnectorListener connectorListener;

//    public void create(DataSourceDTO dataSourceDTO) {
//
//        String conClass = "";
//        String path = new StringBuilder().append("/tmp/").append(dataSourceDTO.getPipId()).append("/").toString();
//
//        switch (dataSourceDTO.getDbType()){
//            case Constant.MYSQL:
//                conClass = Constant.MYSQL_CONNECTOR_CLASS;
//                dataSourceDTO.setServerId(Constant.MYSQL_SERVER_ID);
//                break;
//            case Constant.POSTGRESQL:
//                conClass = Constant.POSTGRESQL_CONNECTOR_CLASS;
//                dataSourceDTO.setServerId(Constant.POSTGRESQL_SERVER_ID);
//                break;
//            case Constant.ORACLE:
//                conClass = Constant.ORACLE_CONNECTOR_CLASS;
//                dataSourceDTO.setServerId(Constant.ORACLE_SERVER_ID);
//                break;
//            case Constant.SQLSERVER:
//                conClass = Constant.SQLSERVER_CONNECTOR_CLASS;
//                dataSourceDTO.setServerId(Constant.SQLSERVER_SERVER_ID);
//                break;
//            case Constant.MONGO:
//                conClass = Constant.MONGO_CONNECTOR_CLASS;
//                dataSourceDTO.setServerId(Constant.MONGO_SERVER_ID);
//                break;
//        }
//
//        Configuration config = Configuration.create()
//                .with("name", dataSourceDTO.getPipName())
//                .with("connector.class", conClass)
//                .with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
//                .with("offset.storage.file.filename", path + Constant.OFFSET_STORAGE_FILE_FILENAME)
//                .with("offset.flush.interval.ms", "60000")
//                .with("database.hostname", dataSourceDTO.getHostName())
//                .with("database.port", dataSourceDTO.getPort())
//                .with("database.user", dataSourceDTO.getUser())
//                .with("database.password", dataSourceDTO.getPassword())
//                .with("database.dbname", dataSourceDTO.getDbName())
//                .with("include.schema.changes", "false")
//                .with("database.server.id", dataSourceDTO.getServerId())
//                .with("database.server.name", "customer-mysql-db-server")
//                .with("database.history", "io.debezium.relational.history.FileDatabaseHistory")
//                .with("database.history.file.filename", path + Constant.DATABASE_HISTORY_FILE_FILENAME)
//                .with("database.allowPublicKeyRetrieval", "true")
//                .with("table.whitelist", "customerdb.customer,customerdb.customer2")// 특정테이블 콤마로 문자열 테이블 구분
//                .build();
//
//        connectorListener.createListner(config);
//
//
//
//    }
}
