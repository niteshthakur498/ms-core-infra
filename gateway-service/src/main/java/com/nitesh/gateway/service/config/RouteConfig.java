package com.nitesh.gateway.service.config;

import com.nitesh.gateway.service.entity.ApiRoute;
import com.nitesh.gateway.service.service.ApiRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Configuration
public class RouteConfig implements RouteLocator{

    private final ApiRouteService apiRouteService;
    private final RouteLocatorBuilder routeLocatorBuilder;



    @Autowired
    private KeyResolver keyResolver;

    @Autowired
    public RouteConfig(ApiRouteService apiRouteService,
                       RouteLocatorBuilder routeLocatorBuilder){
        this.apiRouteService = apiRouteService;
        this.routeLocatorBuilder = routeLocatorBuilder;
    }
/*
    @Override
    public Flux<Route> getRoutes(){
        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        return Flux.fromIterable(apiRouteService.findApiRoutes())
                .map(apiRoute -> routesBuilder.route(String.valueOf(apiRoute.getId()),
                        predicateSpec -> setPredicateSpec(apiRoute, predicateSpec)

                ))
                .collectList()
                .flatMapMany(builders -> routesBuilder.build()
                        .getRoutes());
    }*/

    @Override
    public Flux<Route> getRoutes() {
        log.info("Entered into building rotes " + apiRouteService.findApiRoutes().size() + "  " + apiRouteService.findApiRoutes());
//        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
//        Flux<ApiRoute> apiRouteFlux = Flux.fromStream(apiRouteService.findApiRoutes().stream());
//        apiRouteFlux
//                .flatMap(apiRoute -> {
//                    routesBuilder.route(apiRoute.getId().toString(),
//                            r -> r.path(apiRoute.getRoutePath())
//                                    .filters(f -> f.requestRateLimiter(c ->
//                                            c.setRateLimiter(redisRateLimiter(apiRoute.getReplenishRate(),apiRoute.getBurstCapacity()))
//                                                    .setKeyResolver(hostNameKeyResolver())))
//                                    .uri(apiRoute.getUri())
//                    );
//                    return Mono.just(routesBuilder.build().getRoutes());
//                })
//                .thenMany(Mono.just(routesBuilder.build().getRoutes()));
//                //.flatMapMany(Flux::fromIterable)
//                ;
//        return routesBuilder.build().getRoutes();

        ///   //////////////////////////////////////////////////////////


        RouteLocatorBuilder.Builder routesBuilder = routeLocatorBuilder.routes();
        for(ApiRoute apiRoute:apiRouteService.findApiRoutes()){
            routesBuilder
                    .route(apiRoute.getId().toString(),
                            r->r.path(apiRoute.getRoutePath())
                                    .filters(f->
                                            //f.requestRateLimiter(c->
                                                    //c.setRateLimiter(redisRateLimiter(apiRoute.getReplenishRate(),apiRoute.getBurstCapacity()))
                                                    //c.setRateLimiter(redisRateLimiter())
                                            f.requestRateLimiter().rateLimiter(RedisRateLimiter.class,
                                                            config->config.setReplenishRate(apiRoute.getReplenishRate()).setBurstCapacity(apiRoute.getBurstCapacity()))
                                                    .configure(config -> config.setKeyResolver(keyResolver))
                                            )
                                    .uri(apiRoute.getUri())
                    );
        }
        return routesBuilder.build().getRoutes();
    }



//    @Bean
//    public GatewayFilterFactory<RequestRateLimiterGatewayFilterFactory.Config> rateLimiterFilter() {
//        return new RequestRateLimiterGatewayFilterFactory() {
//            @Override
//            public Mono<Void> denyResponse(ServerWebExchange exchange, String key) {
//                ServerHttpResponse response = exchange.getResponse();
//                response.setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//                response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
//                DataBuffer buffer = response.bufferFactory().wrap("{\"error\": \"RATE_LIMIT_EXCEEDED\", \"message\": \"Too many requests. Please try again later.\"}".getBytes());
//                return response.writeWith(Mono.just(buffer));
//            }
//        };
//    }

    private Buildable<Route> setPredicateSpec(ApiRoute apiRoute, PredicateSpec predicateSpec) {
        BooleanSpec booleanSpec = predicateSpec.path(apiRoute.getRoutePath());
        if (!StringUtils.isEmpty(apiRoute.getMethod())) {
            booleanSpec.and()
                    .method(apiRoute.getMethod());
        }
        return booleanSpec.uri(apiRoute.getUri());
    }



}
