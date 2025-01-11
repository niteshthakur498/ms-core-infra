package com.nitesh.notificationservice.service;

import com.nitesh.notificationservice.dto.NotificationKafkaMessage;

public interface KafkaNotificationProcessService {
    void processNotification(NotificationKafkaMessage notificationKafkaMessage);
}
