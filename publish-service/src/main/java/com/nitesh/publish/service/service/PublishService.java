package com.nitesh.publish.service.service;

import com.nitesh.publish.service.dto.KafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PublishService {

    private final KafkaTemplate kafkaTemplate;

    public void publishService(KafkaMessage kafkaMessage){
        try {
            kafkaTemplate.send(kafkaMessage.getEventCode(), kafkaMessage);
        } catch (Exception e) {
            log.warn("Error raised-->{} caused by {}", e.getMessage(), e.getCause().toString());
        }
    }

}
