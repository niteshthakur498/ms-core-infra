package com.nitesh.notificationservice.service.mail;

import com.nitesh.notificationservice.dto.GenericNotification;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


    public class NotificationChannelService {

        private NotificationSendingService notificationSendingService;

        public NotificationChannelService(NotificationSendingService notificationSendingService){
            this.notificationSendingService = notificationSendingService;
        }

        public void sendNotification(GenericNotification notification,
                                     Map<String, Object> model,
                                     String templateName,
                                     Map<String, MultipartFile> mailInlineImages) throws Exception{
            notificationSendingService.sendNotification(notification,model,templateName,mailInlineImages);  // Delegates to the appropriate service
        }
    }
