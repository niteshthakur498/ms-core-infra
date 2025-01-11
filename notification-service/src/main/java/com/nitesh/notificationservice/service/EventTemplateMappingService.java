package com.nitesh.notificationservice.service;

import com.nitesh.notificationservice.entity.EventTemplateMapping;

import java.util.List;
import java.util.Optional;

public interface EventTemplateMappingService {

    EventTemplateMapping save(EventTemplateMapping eventTemplateMapping);

    List<EventTemplateMapping> findAll();

    public List<EventTemplateMapping> findByEventCode(String eventType);

    Optional<EventTemplateMapping> findById(Long id);

    void deleteById(Long id);
}
