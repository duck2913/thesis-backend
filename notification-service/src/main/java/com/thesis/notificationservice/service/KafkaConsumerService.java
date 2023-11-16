package com.thesis.notificationservice.service;

import com.thesis.notificationservice.dto.EventDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "notifications", groupId = "group1")
    public void receiveMessage(EventDto event) {
        System.out.println(event);
    }
}