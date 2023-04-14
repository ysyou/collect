package com.clamos.io.collect.controller;

import com.clamos.io.collect.dto.TopicDTO;
import com.clamos.io.collect.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    KafkaService kafkaService;
    @Autowired
    KafkaTemplate kafkaTemplate;

    @PostMapping("/show")
    public void show(){
        kafkaService.show();
    }
    @PostMapping("/topic")
    public void regist(@RequestBody @Valid TopicDTO topics){
        kafkaService.registListner(topics::getTopics);
    }
    @DeleteMapping("/topic")
    public void delist(@RequestBody @Valid TopicDTO topics){
        kafkaService.delistListner(topics::getTopics);
    }


    @PostMapping("/pro/send")
    public void producer(){
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("test","test", "testData");
        }
    }

}
