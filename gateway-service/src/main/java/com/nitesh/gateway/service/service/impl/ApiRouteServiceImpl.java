package com.nitesh.gateway.service.service.impl;

import com.nitesh.gateway.service.entity.ApiRoute;
import com.nitesh.gateway.service.service.ApiRouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiRouteServiceImpl implements ApiRouteService {
    @Override
    public List<ApiRoute> findApiRoutes(){
        return List.of(new ApiRoute(1L,"/test-service/**","test-service","lb://test-service","",false,true));
    }
}
