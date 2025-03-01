package com.nitesh.gateway.service.repository;

import com.nitesh.gateway.service.entity.ApiRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiRouteRepository extends JpaRepository<ApiRoute, Long> {
}
