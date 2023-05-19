package com.clamos.io.collect.config;

import com.clamos.io.collect.dto.DataSourceDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.Objects;
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DataSourceDTO> CONTEXT_HOLDER = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT_HOLDER.get().getUrl();

    } //현재 데이터 소스 불러오기

    public static void setContextHolder(DataSourceDTO dataSourceDTO) {
        CONTEXT_HOLDER.set(dataSourceDTO);
    }

    public static DataSourceDTO getDataSource() {
        return CONTEXT_HOLDER.get();
    }

    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}
