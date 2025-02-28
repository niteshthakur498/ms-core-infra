package com.nitesh.publish.service.repository;

import com.nitesh.publish.service.entity.PublishRequestFailed;
import com.nitesh.publish.service.entity.PublishRequestQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishRequestFailedRepository extends JpaRepository<PublishRequestFailed, Long> {
}
