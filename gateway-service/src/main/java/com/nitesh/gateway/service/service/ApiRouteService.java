package com.nitesh.gateway.service.service;

import com.nitesh.gateway.service.entity.ApiRoute;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ApiRouteService {
    List<ApiRoute> findApiRoutes();
}
