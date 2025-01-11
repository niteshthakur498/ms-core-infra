package com.nitesh.notificationservice.service.impl;

import com.nitesh.notificationservice.entity.EventTemplateMapping;
import com.nitesh.notificationservice.repository.EventTemplateMappingRepository;
import com.nitesh.notificationservice.service.EventTemplateMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTemplateMappingServiceImpl implements EventTemplateMappingService {

    private final EventTemplateMappingRepository repository;

    @Override
    @Transactional
    public EventTemplateMapping save(EventTemplateMapping eventTemplateMapping) {
        return repository.save(eventTemplateMapping);
    }

    @Override
    public List<EventTemplateMapping> findAll() {
        return repository.findAll();
    }

    @Override
    public List<EventTemplateMapping> findByEventCode(String eventType) {
        return repository.findByEventCode(eventType);
    }

    @Override
    public Optional<EventTemplateMapping> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

