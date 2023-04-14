package com.clamos.io.collect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class TestConsumService {
    @KafkaListener(topics = "clamos", containerFactory = "kafkaListenerContainerFactory")
    public void empListener(Map data) {
        try {
            log.info("▶▶▶▶▶▶▶▶ RECEIVED DATA FROM KAFKA: " + data.toString());
//            empService.insertEmp(empDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
