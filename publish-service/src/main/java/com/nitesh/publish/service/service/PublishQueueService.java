package com.nitesh.publish.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitesh.publish.service.dto.KafkaMessage;
import com.nitesh.publish.service.entity.PublishRequestQueue;
import com.nitesh.publish.service.repository.PublishRequestQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PublishQueueService {


    private final PublishRequestQueueRepository queueRepository;
//    private final KafkaTemplate kafkaTemplate;
//
//    public void publishService(KafkaMessage kafkaMessage){
//        try {
//            kafkaTemplate.send(kafkaMessage.getEventCode(), kafkaMessage);
//        } catch (Exception e) {
//            log.warn("Error raised-->{} caused by {}", e.getMessage(), e.getCause().toString());
//        }
//    }

    public void publishToQueue(KafkaMessage kafkaMessage){
        try{
            PublishRequestQueue publishRequestQueue = new PublishRequestQueue();
            ObjectMapper objectMapper = new ObjectMapper();
            publishRequestQueue.setSource(kafkaMessage.getSource());
            publishRequestQueue.setEventCode(kafkaMessage.getEventCode());
            publishRequestQueue.setMessageContent(objectMapper.writeValueAsString(kafkaMessage));
            queueRepository.save(publishRequestQueue);
        } catch (Exception e) {
            log.warn("Error raised-->{} caused by {}", e.getMessage(), e.getCause().toString());
        }
    }

}
