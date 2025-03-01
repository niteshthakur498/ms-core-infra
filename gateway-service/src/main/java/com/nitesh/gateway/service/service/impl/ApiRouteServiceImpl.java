package com.nitesh.gateway.service.service.impl;

import com.nitesh.gateway.service.entity.ApiRoute;
import com.nitesh.gateway.service.service.ApiRouteService;
import com.nitesh.gateway.service.repository.ApiRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ApiRouteServiceImpl implements ApiRouteService {


    private final ApiRouteRepository apiRouteRepository;

    @Override
    public List<ApiRoute> findApiRoutes(){
        //return List.of(new ApiRoute(1L,"/test-service/**","test-service","lb://test-service","",false,true));
        return apiRouteRepository.findAll();
    }
}
