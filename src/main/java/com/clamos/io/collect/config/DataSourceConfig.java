package com.clamos.io.collect.config;

import com.clamos.io.collect.dto.DataSourceDTO;
import com.clamos.io.collect.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@Configuration
@ComponentScan(lazyInit = true)
@RequiredArgsConstructor
@Slf4j
public class DataSourceConfig {
    private static final Map<Object, Object> targetDataSources = new HashMap<>();
    private static final DynamicDataSource dynamicDataSource = new DynamicDataSource();
    private final CommonService commonService;

    public DataSource createDataSource(DataSourceDTO dataSourceDTO) {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        String url = commonService.makeUrl(dataSourceDTO);
        String driverName = commonService.getDriverName(dataSourceDTO.getDbType());
        DataSource build = dataSourceBuilder
                .driverClassName(driverName)
                .url(dataSourceDTO.getUrl())
                .username(dataSourceDTO.getDbUser())
                .password(dataSourceDTO.getDbPassword()).build();
        return build;

    }

    public void addDataSource(DataSourceDTO dataSourceDTO) throws SQLException {
        targetDataSources.put(dataSourceDTO.getUrl(), dataSourceDTO.getDataSource()); //나중에 key url말고 다른걸로 변경 해야됨 같은 디비 수집하면 어쩔껀데
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setContextHolder(dataSourceDTO);
//        dynamicDataSource.setDefaultTargetDataSource(dataSource);
        dynamicDataSource.afterPropertiesSet();
    }

    @Bean
    public DynamicDataSource dataSource() {
        //처음 bean 은 그냥 빈값
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }
}
