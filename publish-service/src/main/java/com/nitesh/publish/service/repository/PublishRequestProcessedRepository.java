package com.nitesh.publish.service.repository;

import com.nitesh.publish.service.entity.PublishRequestProcessed;
import com.nitesh.publish.service.entity.PublishRequestQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishRequestProcessedRepository extends JpaRepository<PublishRequestProcessed, Long> {
}
