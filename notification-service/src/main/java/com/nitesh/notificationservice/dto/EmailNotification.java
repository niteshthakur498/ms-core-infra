package com.nitesh.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification implements GenericNotification {


    String fromMail;

    @NotNull
    List<String> toMail;

    List<String> ccMail;

    List<String> bccMail;

    @NotNull
    @NotBlank
    String subject;

    List<String> attachmentUrl;
}
