package com.nitesh.gateway.service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "API_ROUTE")
public class ApiRoute {

    @Id
    private Long id;

    @Column(name = "route_path")
    private String routePath;

    @Column(name = "service_id")
    private String serviceId;

    private String uri;

    private String method;

    @Column(name = "strip_prefix")
    private Boolean stripPrefix;

    private Boolean active;

    @Column(name = "replenish_rate")
    private Integer replenishRate;

    @Column(name = "burst_capacity")
    private Integer burstCapacity;

}

