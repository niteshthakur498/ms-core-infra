package com.nitesh.notificationservice.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.thymeleaf.templateresource.ITemplateResource;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

@AllArgsConstructor
@Getter
public class DatabaseTemplateResource implements ITemplateResource {

    private final String templateName;
    private final String subject;
    private final String templateContent;

    @Override
    public String getDescription(){
        return "Database template: " + templateName;
    }

    @Override
    public String getBaseName(){
        return templateName;
    }

    @Override
    public boolean exists(){
        return templateContent != null && !templateContent.isEmpty();
    }

    @Override
    public Reader reader() throws IOException{
        return new StringReader(templateContent);
    }

    @Override
    public ITemplateResource relative(String relativeLocation){
        return null;
    }
}
