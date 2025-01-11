package com.nitesh.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTemplateMappingDTO {

    private String eventType; // Type of the event (e.g., 'user_signup', 'order_shipped')
    private Integer templateId; // ID of the template to be used
    private String description; // Optional description of the event type
    private LocalDateTime createdAt; // Timestamp when the mapping was created
    private LocalDateTime updatedAt; // Timestamp of the last update
}

