package com.clamos.io.collect.service;

import com.clamos.io.collect.constants.Constant;
import com.clamos.io.collect.dto.DataSourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonService {

    public String getDriverName(String dataBaseType){
        String conClass = "";
        switch (dataBaseType){
            case Constant.MYSQL:
                conClass = Constant.MYSQL_DRIVER_CLASS_NAME;
                break;
            case Constant.POSTGRESQL:
                conClass = Constant.POSTGRESQL_DRIVER_CLASS_NAME;
                break;
            case Constant.ORACLE:
                conClass = Constant.ORACLE_DRIVER_CLASS_NAME;
                break;
            case Constant.SQLSERVER:
                conClass = Constant.SQLSERVER_DRIVER_CLASS_NAME;
                break;
            case Constant.MONGO:
                conClass = Constant.MONGO_DRIVER_CLASS_NAME;
                break;
        }
        return conClass;
    }
//    public String makeUrl(DataSourceDTO dataSourceDTO){
//        String url = "";
//        switch (dataSourceDTO.getDbType()){
//            case Constant.MYSQL:
//                //jdbc:mysql://192.168.0.130:3305/customerdb
//                url = new StringBuilder().append("jdbc:mysql://").append(dataSourceDTO.getHost()).append(":").append(dataSourceDTO.getPort()).append("/").append(dataSourceDTO.getDbName()).toString();
//                break;
//            case Constant.POSTGRESQL:
//                //jdbc:postgresql://192.168.0.130:5432/DTrixData
//                url = new StringBuilder().append("jdbc:postgresql://").append(dataSourceDTO.getHost()).append(":").append(dataSourceDTO.getPort()).append("/").append(dataSourceDTO.getDbName()).toString();
//                break;
//            case Constant.ORACLE:
//                //jdbc:oracle:thin:@localhost:1521:XE
//                url = new StringBuilder().append("jdbc:oracle:").toString();
//                break;
//            case Constant.SQLSERVER:
//                //jdbc:sqlserver://localhost\tete:1234;database=test
//                url = new StringBuilder().append("jdbc:sqlserver://").toString();
//                break;
//            case Constant.MONGO:
//                //mongodb://localhost:27017/test
//                url = new StringBuilder().append("mongodb://").toString();
//                break;
//        }
//        return url;
//    }
}
