package com.nitesh.gateway.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.Objects;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}


	@Bean
	//public RedisRateLimiter redisRateLimiter(int replenishRate, int burstCapacity) {
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(2, 3);
	}
	private final String keyPrefix = "gateway-service";
	@Bean
	public KeyResolver hostNameKeyResolver() {
		return exchange ->
				Mono.just(keyPrefix + Objects.requireNonNull(exchange.getRequest().getRemoteAddress()).getHostName());
	}
}
