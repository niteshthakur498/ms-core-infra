package com.nitesh.notificationservice.repository;

import com.nitesh.notificationservice.entity.NotificationTemplate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventTemplateRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<NotificationTemplate> findNotificationsByTemplateAndStatus(Long templateId){
        String query = "SELECT t FROM Notification_template t JOIN event_template_mapping e WHERE t.id = e.template_id and e.template_id = :templateId";

        return entityManager.createNativeQuery(query, NotificationTemplate.class).setParameter("templateId", templateId)
                //.setParameter("status", status)
                .getResultList();
    }



}
