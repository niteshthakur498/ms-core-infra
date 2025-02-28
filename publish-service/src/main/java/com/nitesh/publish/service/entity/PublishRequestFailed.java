package com.nitesh.publish.service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PUBLISH_REQUEST_FAILED")
public class PublishRequestFailed extends PublishRequestQueueBase{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_seq_generator"
    )
    @SequenceGenerator(name = "id_seq_generator", sequenceName = "seq_PUBLISH_REQUEST_FAILED", allocationSize = 1)
    private Long id;


    public static PublishRequestFailed build(PublishRequestQueue publishRequestQueue){
        PublishRequestFailed publishRequestFailed = new PublishRequestFailed();

        publishRequestFailed.setSource(publishRequestQueue.getSource());
        publishRequestFailed.setEventCode(publishRequestQueue.getEventCode());
        publishRequestFailed.setMessageContent(publishRequestQueue.getMessageContent());
        publishRequestFailed.setCreatedAt(publishRequestQueue.getCreatedAt());
        publishRequestFailed.setUpdatedAt(publishRequestQueue.getUpdatedAt());
        publishRequestFailed.setStatus("P");
        publishRequestFailed.setRetryCount(publishRequestQueue.getRetryCount()+1);
        publishRequestFailed.setLastAttemptTimestamp(publishRequestQueue.getLastAttemptTimestamp());

        return publishRequestFailed;
    }
}
