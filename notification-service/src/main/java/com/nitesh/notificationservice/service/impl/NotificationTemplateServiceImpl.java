package com.nitesh.notificationservice.service.impl;

import com.nitesh.notificationservice.entity.NotificationTemplate;
import com.nitesh.notificationservice.repository.NotificationTemplateRepository;
import com.nitesh.notificationservice.service.NotificationTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class NotificationTemplateServiceImpl implements NotificationTemplateService {

    private final NotificationTemplateRepository notificationTemplateRepository;

    @Override
    public NotificationTemplate getTemplateById(Long id){
        return notificationTemplateRepository.findById(id).orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    public NotificationTemplate getTemplateByName(String templateName){
        return notificationTemplateRepository.findByTemplateName(templateName).orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    @Transactional
    public NotificationTemplate saveTemplate(NotificationTemplate template){
        return notificationTemplateRepository.save(template);
    }

}
