package com.nitesh.publish.service.entity;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class PublishRequestQueueBase {

    @Column(name = "event_code", nullable = false, length = 255)
     String eventCode;

    @Column(name = "source", nullable = false, length = 255)
     String source;

    @Column(name = "message_content", nullable = false, columnDefinition = "jsonb")
     String messageContent; // Use Jackson JsonNode for JSONB fields

    @Column(name = "status", nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'U'")
     String status = "U"; // Default to 'U' for Unprocessed

    @Column(name = "retry_count", nullable = false)
     Integer retryCount = 0; // Default to 0

    @Column(name = "last_attempt_timestamp")
     LocalDateTime lastAttemptTimestamp;

    @Column(name = "created_at", updatable = false)
     LocalDateTime createdAt;

    @Column(name = "updated_at")
     LocalDateTime updatedAt;

    @PrePersist
     void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
     void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
