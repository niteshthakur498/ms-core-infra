package com.nitesh.publish.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage implements Serializable {

    @NotBlank
    @NotNull
    private String eventCode;//event driven notification service and also find the templates mapped for given event

    @NotBlank
    @NotNull
    private String source;

    @NotNull
    @NotBlank
    private LocalDateTime timestamp;

    Map<String,Object> payload;// for variables which ever are required will be rendered into template to create final message

    List<String> multiMediaUrls;


    private String correlationId;



}
