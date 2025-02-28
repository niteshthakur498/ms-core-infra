package com.nitesh.publish.service.repository;

import com.nitesh.publish.service.entity.PublishRequestQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishRequestQueueRepository extends JpaRepository<PublishRequestQueue, Long> {

    @Query("SELECT r FROM PublishRequestQueue r WHERE r.status = 'U'")
    List<PublishRequestQueue> findUnprocessedRequests();
}
