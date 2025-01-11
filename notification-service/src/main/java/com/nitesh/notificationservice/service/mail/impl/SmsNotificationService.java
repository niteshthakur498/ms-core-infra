package com.nitesh.notificationservice.service.mail.impl;

import com.nitesh.notificationservice.dto.GenericNotification;
import com.nitesh.notificationservice.dto.SmsNotification;
import com.nitesh.notificationservice.service.mail.NotificationSendingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class SmsNotificationService implements NotificationSendingService {
    @Override
    public void sendNotification(GenericNotification notification,
                                 Map<String, Object> model,
                                 String templateName,
                                 Map<String, MultipartFile> mailInlineImages){
        if(notification instanceof SmsNotification){
            SmsNotification smsNotification = (SmsNotification) notification;

        }else{
            throw new IllegalArgumentException("Invalid notification type for SMS service");
        }
    }
}
