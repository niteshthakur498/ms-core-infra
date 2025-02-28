package com.nitesh.publish.service.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PUBLISH_REQUEST_QUEUE")
public class PublishRequestQueue extends PublishRequestQueueBase{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_seq_generator"
    )
    @SequenceGenerator(name = "id_seq_generator", sequenceName = "seq_PUBLISH_REQUEST_QUEUE", allocationSize = 1)
    private Long id;
}
