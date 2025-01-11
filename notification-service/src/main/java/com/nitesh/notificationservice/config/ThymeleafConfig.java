package com.nitesh.notificationservice.config;

import com.nitesh.notificationservice.repository.NotificationTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Configuration
public class ThymeleafConfig {
    @Autowired
    NotificationTemplateRepository templateRepository;

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Register the custom resolver (DatabaseTemplateResolver)
        templateEngine.setTemplateResolver(databaseTemplateResolver());

//        templateEngine.addTemplateResolver(new SpringResourceTemplateResolver());
//        templateEngine.addTemplateResolver(new StringTemplateResolver());
//        templateEngine.addTemplateResolver(new FileTemplateResolver());
        return templateEngine;
    }


    public ITemplateResolver databaseTemplateResolver() {
        // This is where we use your custom DatabaseTemplateResolver

        return new DatabaseTemplateResolver(templateRepository);
    }

    @Bean
    public IEngineConfiguration engineConfiguration(SpringTemplateEngine templateEngine) {
        return templateEngine.getConfiguration();
    }
}
