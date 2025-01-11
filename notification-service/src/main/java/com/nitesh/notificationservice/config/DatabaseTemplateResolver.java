package com.nitesh.notificationservice.config;

import com.nitesh.notificationservice.entity.NotificationTemplate;
import com.nitesh.notificationservice.exception.TemplateNotFoundException;
import com.nitesh.notificationservice.repository.NotificationTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.cache.ICacheEntryValidity;
import org.thymeleaf.cache.TTLCacheEntryValidity;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.thymeleaf.templateresource.ITemplateResource;
import org.thymeleaf.templateresource.StringTemplateResource;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DatabaseTemplateResolver implements ITemplateResolver {


    private final NotificationTemplateRepository templateRepository;

    @Override
    public String getName(){
        return "";
    }

    @Override
    public Integer getOrder(){
        return 0;
    }

    @Override
    public TemplateResolution resolveTemplate(IEngineConfiguration configuration,
                                              String ownerTemplate,
                                              String template,
                                              Map<String, Object> templateResolutionAttributes){
        Optional<NotificationTemplate> templateEntity = templateRepository.findByTemplateName(template);

        if(templateEntity.isEmpty()){
            throw new TemplateNotFoundException("Template does not Exist for "+template);
        }
        ITemplateResource templateResource = new DatabaseTemplateResource(template,templateEntity.get().getSubject(),templateEntity.get().getBody());
        //ITemplateResource  templateResource = new StringTemplateResource(templateEntity.get().getBody());

        TemplateMode templateMode = switch(templateEntity.get().getTemplateType()){
            case "email" -> TemplateMode.HTML;
            case "sms", "push" -> TemplateMode.TEXT;
            default -> TemplateMode.HTML;
        };
        ICacheEntryValidity validity = new TTLCacheEntryValidity(10);
        return new TemplateResolution(templateResource,templateMode,validity);
    }
}
