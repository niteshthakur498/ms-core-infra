package com.nitesh.publish.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

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

    HashMap<String,Object> payload;// for variables which ever are required will be rendered into template to create final message

    ArrayList<String> multiMediaUrls;

    private String correlationId;

    //schema version number

}
