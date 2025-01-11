package com.nitesh.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification_message_log")
public class NotificationMessageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for the notification log entry.

    @Column(name = "event_type", nullable = false)
    private String eventType;  // Event type that triggered the notification (e.g., 'order_shipped').

    @Column(name = "user_id", nullable = false)
    private Integer userId;  // User ID that the notification was sent to.

    @Column(name = "channel", nullable = false)
    private String channel;  // User ID that the notification was sent to.

    @Column(name = "template_id", nullable = false)
    private Integer templateId;  // Template ID used to generate the message (foreign key to notification_templates).

    @Column(name = "message_content")
    private String messageContent;  // Final content of the message in JSON/TEXT format (email body, etc.).

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'PENDING'")
    private String status = "PENDING";  // Status of the notification (e.g., 'PENDING', 'SENT', 'FAILED').

    @Column(name = "sent_at")
    private LocalDateTime sentAt;  // Timestamp of when the notification was sent.

    @Column(name = "error_message")
    private String errorMessage;  // Error message in case of failure.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Timestamp when the record was created.

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();  // Timestamp of the last update.

    @PrePersist
    public void onPrePersist(){
        // Automatically set the creation and update timestamps before saving
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onPreUpdate(){
        // Automatically update the updated timestamp before updating the record
        this.updatedAt = LocalDateTime.now();
    }

}
