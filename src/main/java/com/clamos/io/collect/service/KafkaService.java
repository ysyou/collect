package com.clamos.io.collect.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    AdminClient adminClient;
    Map<String, NewTopic> registeredTopicMap = new ConcurrentHashMap<>();
    @Autowired
    KafkaListenerContainerFactory kafkaListenerContainerFactory;
    Object lock = new Object();

    public void registListner(final Supplier<Set<String>> topicSupplier) {
        Assert.notNull(topicSupplier, "topicSupplier must be not null.");

        synchronized (lock) {
            final Set<String> registeredTopics = getRegisteredTopics();
            final Set<String> topics = topicSupplier.get();

            if (topics.isEmpty()) {
                return;
            }

            topics.stream()
                    .filter(topic -> !registeredTopics.contains(topic))
                    .forEach(topic -> doRegister(topic));
        }
    }

    private void doRegister(final String topic) {
        Assert.hasLength(topic, "토픽명이 빈값이면 안됩니다.");

        //서버 댓수 확인해서 partion 생성
        NewTopic newTopic = new NewTopic(topic, 1, (short)1);

//        아래거는 topic 컨테이너를 만드는 방법
//        final MessageListenerContainer messageListenerContainer = kafkaListenerContainerFactory.createContainer(topic);
//        messageListenerContainer.setupMessageListener(messageListener);
//        messageListenerContainer.start();


//      이건 topic 자치를 만드는거
        Collection<NewTopic> newTopicList = new ArrayList<>();
        newTopicList.add(newTopic);
        adminClient.createTopics(newTopicList);
        registeredTopicMap.put(topic, newTopic);
    }
    public void delistListner(final Supplier<Set<String>> topicSupplier) {
        Assert.notNull(topicSupplier, "topicSupplier must be not null.");

        synchronized (lock) {
            final Set<String> registeredTopics = getRegisteredTopics();
            final Set<String> topics = topicSupplier.get();

            if (topics.isEmpty()) {
                return;
            }
            topics.stream()
                    .filter(registeredTopics::contains)
                    .forEach(topic -> doDelist(topic));
        }
    }
    private void doDelist(final String topic) {
        Assert.hasLength(topic, "topic must be not empty.");
        Collection<String> newTopicList = new ArrayList<>();
        newTopicList.add(topic);
        adminClient.deleteTopics(newTopicList);
        registeredTopicMap.remove(topic);
    }
    private Set<String> getRegisteredTopics() {
        return registeredTopicMap.keySet();
    }
    public void show() {
        log.info("map : {}", registeredTopicMap);
    }
}
