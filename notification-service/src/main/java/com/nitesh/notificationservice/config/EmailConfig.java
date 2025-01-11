package com.nitesh.notificationservice.config;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Load properties from application.properties
        mailSender.setHost("smtp.gmail.com");  // You can get this from your properties file
        mailSender.setPort(587);                        // You can get this from your properties file
        mailSender.setUsername("niteshitsme@gmail.com"); // Set the email username
        mailSender.setPassword("TEST-PASSWORD");    // Set the email password

        // Set the mail properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public MimeMessageHelper mimeMessageHelper(JavaMailSender mailSender) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        return new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
    }

}
