package com.nitesh.notificationservice.service.mail;

import com.nitesh.notificationservice.dto.GenericNotification;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface NotificationSendingService {
    void sendNotification(GenericNotification notification,
                          Map<String, Object> model,
                          String templateName,
                          Map<String, MultipartFile> mailInlineImages) throws Exception;
}
