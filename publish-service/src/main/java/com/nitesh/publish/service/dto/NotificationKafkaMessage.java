package com.nitesh.publish.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationKafkaMessage {

    @NotBlank
    @NotNull
    private String event_id;//event driven notification service and also find the templates mapped for given event

    Map<String,Object> model;// for template variables which ever are required will be rendered into template to create final message

    List<String> multiMediaUrls;

}
