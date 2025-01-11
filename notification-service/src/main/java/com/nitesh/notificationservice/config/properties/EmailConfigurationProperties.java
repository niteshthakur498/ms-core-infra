package com.nitesh.notificationservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfigurationProperties {

    private String username;

    private String password;

}
