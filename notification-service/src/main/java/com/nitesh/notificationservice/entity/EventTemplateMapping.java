package com.nitesh.notificationservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_template_mapping")
@Data
@NoArgsConstructor
public class EventTemplateMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for the event-template mapping

    @Column(name = "event_code", nullable = false, length = 255)
    private String eventCode; // Type of the event (e.g., 'user_signup', 'order_shipped')

    @Column(name = "template_name", nullable = false)
    private String templateName; // ID of the template to be used (foreign key to notification_templates)

    @Column(name="template_type", nullable = false)
    private String templateType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // Optional description of the event type

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Timestamp when the mapping was created

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now(); // Timestamp of the last update
}

