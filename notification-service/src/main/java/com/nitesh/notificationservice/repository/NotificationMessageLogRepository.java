package com.nitesh.notificationservice.repository;

import com.nitesh.notificationservice.entity.NotificationMessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationMessageLogRepository extends JpaRepository<NotificationMessageLog, Long> {

    // Custom query to find logs by event type and status
    List<NotificationMessageLog> findByEventTypeAndStatus(String eventType,
                                                          String status);

    // Custom query to find the most recent log for a user and event type
    Optional<NotificationMessageLog> findTopByUserIdAndEventTypeOrderByCreatedAtDesc(Integer userId,
                                                                                     String eventType);

    // Custom query to find all logs by userId
    List<NotificationMessageLog> findByUserId(Integer userId);

    // Custom query to find all logs with a failed status
    List<NotificationMessageLog> findByStatus(String status);
}
