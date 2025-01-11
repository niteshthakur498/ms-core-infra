package com.nitesh.notificationservice.repository;

import com.nitesh.notificationservice.entity.EventTemplateMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTemplateMappingRepository extends JpaRepository<EventTemplateMapping, Long> {
    // Custom queries can be added here, if needed
    List<EventTemplateMapping> findByEventCode(String eventCode);

}