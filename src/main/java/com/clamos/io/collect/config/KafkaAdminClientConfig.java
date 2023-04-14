package com.clamos.io.collect.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAdminClientConfig {
    @Value("${link.kafka.bootStrapAddress}")
    private String bootStrapAddress;

    @Bean
    public AdminClient adminClient() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapAddress);
        configs.put(AdminClientConfig.CLIENT_ID_CONFIG, "clamos");
        return AdminClient.create(configs);
    }
}
