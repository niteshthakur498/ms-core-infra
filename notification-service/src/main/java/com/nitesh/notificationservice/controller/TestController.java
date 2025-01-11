package com.nitesh.notificationservice.controller;

import com.nitesh.notificationservice.config.properties.EmailConfigurationProperties;
import com.nitesh.notificationservice.dto.EmailNotification;
import com.nitesh.notificationservice.dto.NotificationKafkaMessage;
import com.nitesh.notificationservice.service.impl.KafkaNotificationProcessingServiceImpl;
import com.nitesh.notificationservice.service.mail.NotificationChannelService;
import com.nitesh.notificationservice.service.mail.impl.EmailNotificationService;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@EnableConfigurationProperties(EmailConfigurationProperties.class)
public class TestController {

    private final KafkaNotificationProcessingServiceImpl kafkaNotificationProcessingService;

    @Autowired
    private EmailConfigurationProperties emailConfigurationProperties;



    @GetMapping("/test")
    public ResponseEntity<String> thymeleafTest(){


                Map<String,Object> model = new HashMap<>();
        model.put("textString","Nitesh");
        NotificationKafkaMessage notificationKafkaMessage = NotificationKafkaMessage.builder().userId(1234L)
                .event_id("TEST_EVENT")
                .model(model)
                .build();

                try{
                    kafkaNotificationProcessingService.processNotification(notificationKafkaMessage);
                } catch(Exception e){
                    log.warn("Error raised-->{}", e.getMessage());
                    log.warn(Arrays.toString(e.getStackTrace()));
                }

        return ResponseEntity.status(HttpStatus.OK).body("mailsent....");
    }
}
