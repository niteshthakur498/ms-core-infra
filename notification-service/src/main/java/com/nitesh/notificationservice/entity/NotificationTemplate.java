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
@Table(name = "notification_templates")
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique ID for the template.

    @Column(name = "template_name", nullable = false, length = 255, unique = true)
    private String templateName;  // Name of the template (e.g., 'order_shipped_email').

    @Column(name = "template_type", nullable = false, length = 50)
    private String templateType;  // Type of template (e.g., 'email', 'sms').

    @Column(name = "subject", length = 255)
    private String subject;  // Subject for the email template (optional).

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;  // Body of the template (HTML or plain text).

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  // Timestamp when the template was created.

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();  // Timestamp when the template was last updated.

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
