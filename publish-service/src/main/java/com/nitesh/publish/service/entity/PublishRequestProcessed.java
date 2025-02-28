package com.nitesh.publish.service.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PUBLISH_REQUEST_PROCESSED")
public class PublishRequestProcessed extends PublishRequestQueueBase{

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_seq_generator"
    )
    @SequenceGenerator(name = "id_seq_generator", sequenceName = "seq_PUBLISH_REQUEST_PROCESSED", allocationSize = 1)
    private Long id;

    @Column(name = "processed_date")
    private LocalDateTime processedDate;

    public static PublishRequestProcessed build(PublishRequestQueue publishRequestQueue){
        PublishRequestProcessed publishRequestProcessed = new PublishRequestProcessed();

        publishRequestProcessed.setSource(publishRequestQueue.getSource());
        publishRequestProcessed.setEventCode(publishRequestQueue.getEventCode());
        publishRequestProcessed.setMessageContent(publishRequestQueue.getMessageContent());
        publishRequestProcessed.setCreatedAt(publishRequestQueue.getCreatedAt());
        publishRequestProcessed.setUpdatedAt(publishRequestQueue.getUpdatedAt());
        publishRequestProcessed.setStatus("P");
        publishRequestProcessed.setRetryCount(publishRequestQueue.getRetryCount()+1);
        publishRequestProcessed.setLastAttemptTimestamp(publishRequestQueue.getLastAttemptTimestamp());

        return publishRequestProcessed;
    }
}
