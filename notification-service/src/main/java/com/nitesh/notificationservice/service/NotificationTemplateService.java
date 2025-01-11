package com.nitesh.notificationservice.service;

import com.nitesh.notificationservice.entity.NotificationTemplate;

public interface NotificationTemplateService {

    NotificationTemplate getTemplateById(Long id);

    NotificationTemplate getTemplateByName(String templateName);

    NotificationTemplate saveTemplate(NotificationTemplate template);
}
