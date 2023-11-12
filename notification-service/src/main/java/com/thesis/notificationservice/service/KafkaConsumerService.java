package com.thesis.notificationservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "test", groupId = "group1")
    public void receiveMessage(Object message) {
        System.out.println("Received message: " + message);
    }
}