package com.nitesh.publish.service.controller;

import com.nitesh.publish.service.dto.KafkaMessage;
import com.nitesh.publish.service.service.PublishQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PublishController {

    private final PublishQueueService publishQueueService;

    @PostMapping("publish-message")
    public ResponseEntity<String> publishMessage(@RequestBody KafkaMessage kafkaMessage){
        publishQueueService.publishToQueue(kafkaMessage);
        return ResponseEntity.status(HttpStatus.OK).body("Publish Started");
    }

}
