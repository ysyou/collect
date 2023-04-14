package com.clamos.io.collect.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;

@Service
@AllArgsConstructor
public class TestPublishService {


    final KafkaTemplate<String, Map> kafkaTemplate;
    public void save(Map data) {
        ListenableFuture<SendResult<String, Map>> future = kafkaTemplate.send("clamos", data);

        // 아래의 콜백은 옵션 사항
        future.addCallback(new ListenableFutureCallback<SendResult<String, Map>>() {
            @Override
            public void onSuccess(SendResult<String, Map> result) {
                Map map = result.getProducerRecord().value();
                System.out.println("Sent message=[" + map.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println( "Unable to send message=[" + data.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}
