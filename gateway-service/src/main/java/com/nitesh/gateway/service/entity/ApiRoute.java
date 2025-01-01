package com.nitesh.gateway.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRoute {
    private Long id;

    private String routePath;
    private String serviceId;
    private String uri;
    private String method;
    private Boolean stripPrefix;
    private boolean active;


}
