package com.nitesh.notificationservice.service.impl;

import com.nitesh.notificationservice.dto.EmailNotification;
import com.nitesh.notificationservice.dto.NotificationKafkaMessage;
import com.nitesh.notificationservice.entity.EventTemplateMapping;
import com.nitesh.notificationservice.service.EventTemplateMappingService;
import com.nitesh.notificationservice.service.KafkaNotificationProcessService;
import com.nitesh.notificationservice.service.UserPreferenceService;
import com.nitesh.notificationservice.service.mail.NotificationChannelService;
import com.nitesh.notificationservice.service.mail.impl.EmailNotificationService;
import com.nitesh.notificationservice.service.mail.impl.SmsNotificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaNotificationProcessingServiceImpl implements KafkaNotificationProcessService {

    private final UserPreferenceService userPreferenceService;
    private final EmailNotificationService emailNotificationService;
    private final EventTemplateMappingService eventTemplateMappingService;
    private final SmsNotificationService smsNotificationService;


    @Override
    @Transactional
    public void processNotification(NotificationKafkaMessage notificationKafkaMessage){
        Object userPref = userPreferenceService.getUserPreferences(1L);
        List<EventTemplateMapping> templateMappings = eventTemplateMappingService.findByEventCode("order_shipped_email");

        for(EventTemplateMapping eventTemplateMapping : templateMappings){
            if(eventTemplateMapping.getTemplateType().equals("email")){
                String emailRequired = "Y";
                if(emailRequired.equals("Y")){
                    NotificationChannelService notificationChannelService = new NotificationChannelService(emailNotificationService);
                    EmailNotification mail = EmailNotification.builder()
                            //.fromMail("test")
                            .toMail(List.of("niteshthakur0498@gmail.com"))//build based on userPref
                            .ccMail(null)
                            .bccMail(null)
                            .subject("Notification Service Test Mail")//getFrom Template
                            .build();
                    try{
                        notificationChannelService.sendNotification(mail,notificationKafkaMessage.getModel(),eventTemplateMapping.getTemplateName(),null);
                    } catch(Exception e){
                        log.warn("Error raised-->{}", e.getMessage());
                        log.warn(Arrays.toString(e.getStackTrace()));
                    }
                }
            }
        }


    }
}
