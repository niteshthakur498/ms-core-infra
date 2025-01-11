package com.nitesh.notificationservice.service.mail.impl;

import com.nitesh.notificationservice.config.DatabaseTemplateResolver;
import com.nitesh.notificationservice.dto.EmailNotification;
import com.nitesh.notificationservice.dto.GenericNotification;
import com.nitesh.notificationservice.service.mail.NotificationSendingService;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailNotificationService implements NotificationSendingService {


    private final TemplateEngine templateEngine;
    private final DatabaseTemplateResolver databaseTemplateResolver;

    private final MimeMessageHelper helper;

    @Override
    public void sendNotification(GenericNotification notification,
                                 Map<String, Object> model,
                                 String templateName,
                                 Map<String, MultipartFile> mailInlineImages) throws Exception{
        if(notification instanceof EmailNotification emailNotification){

            /*TemplateResolution resolution = databaseTemplateResolver.resolveTemplate(
                    engineConfiguration,   // Engine configuration
                    "",     // Name of the owner template (can be null if not needed)
                    templateName,          // The name of the template to resolve from the database
                    model     // Map of dynamic attributes/context
                );
            DatabaseTemplateResource templateResource = (DatabaseTemplateResource) resolution.getTemplateResource();
             */
            Context context = new Context();
            log.info(model.toString());
            context.setVariables(model);
            String response = templateEngine.process("order_shipped_email",context);


            //Mail Structure Build
            setUpMailContent(emailNotification);
            setUpInlineImages(mailInlineImages);
            helper.setText(response, true);


            //javaMailSender.send(message);
            log.info("Mail Body Text "+response);
            log.info("Helper Text-->"+helper.toString());

        }else{
            throw new IllegalArgumentException("Invalid notification type for Email service");
        }
    }

    private void setUpMailContent(EmailNotification emailNotification) throws MessagingException{
        if(emailNotification.getFromMail()!=null)
            helper.setFrom(emailNotification.getFromMail());

        for(String fromMail : emailNotification.getToMail())
            helper.addTo(fromMail);

        if(emailNotification.getCcMail() != null)
            for(String ccMail : emailNotification.getCcMail())
                helper.addTo(ccMail);

        if(emailNotification.getBccMail() != null)
            for(String bccMail : emailNotification.getBccMail())
                helper.addTo(bccMail);

        helper.setSubject(emailNotification.getSubject());




                // Attach other files if provided
                if (emailNotification.getAttachmentUrl() != null) {
                    for (String url : emailNotification.getAttachmentUrl()) {
                        log.info(url);
                    }
                }
    }

    private void setUpInlineImages(Map<String, MultipartFile> mailInlineImages) throws MessagingException{
        // Attach inline images
        if (mailInlineImages != null ) {
            for (Map.Entry<String, MultipartFile> entry : mailInlineImages.entrySet()) {
                try {
                    String contentId = entry.getKey();  // e.g., "logoImage"
                    MultipartFile inlineFile = entry.getValue();
                    // Convert MultipartFile to DataSource
                    DataSource dataSource = new ByteArrayDataSource(inlineFile.getBytes(), inlineFile.getContentType());
                    // Add as inline image
                    helper.addInline(contentId, dataSource);
                } catch (IOException e) {
                    log.warn("Exception for inline image processing  {}", e.getMessage());
                }
            }
        }
    }
}
