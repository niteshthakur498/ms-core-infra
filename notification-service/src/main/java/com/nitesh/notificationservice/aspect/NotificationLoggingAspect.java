package com.nitesh.notificationservice.aspect;

import com.nitesh.notificationservice.entity.NotificationMessageLog;
import com.nitesh.notificationservice.repository.NotificationMessageLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class NotificationLoggingAspect {

    private final NotificationMessageLogRepository logRepository;

    @Pointcut("execution(* com.nitesh.notificationservice.service.mail.NotificationSendingService.sendNotification(..))")
    public void notificationServiceMethods(){}

    @Around("notificationServiceMethods()")
    public Object logNotification(ProceedingJoinPoint joinPoint) throws Throwable{
        log.debug("Notification method execution started: {} at {}", joinPoint.getSignature().getName(), LocalDateTime.now());
        notificationLogCreate(joinPoint);
        // Proceed with the actual method execution
        Object result = joinPoint.proceed();
        notificationLogUpdate(joinPoint, result);
        log.debug("Notification method execution ended: {} at {}", joinPoint.getSignature().getName(), LocalDateTime.now());

        return result;
    }

    public void notificationLogCreate(JoinPoint joinPoint){
        log.debug("Creating Log record...");
        // Get method arguments (if needed)
//        String eventType = (String) joinPoint.getArgs()[0];
//        Integer userId = (Integer) joinPoint.getArgs()[1];
//        Integer templateId = (Integer) joinPoint.getArgs()[2];
//        String messageContent = (String) joinPoint.getArgs()[3];
//
//        // Create a new log entry for 'PENDING' status
//        NotificationMessageLog log = new NotificationMessageLog();
//        log.setEventType(eventType);
//        log.setUserId(userId);
//        log.setTemplateId(templateId);
//        log.setMessageContent(messageContent);
//        log.setStatus("PENDING");
//        log.setSentAt(LocalDateTime.now());
//        logRepository.save(log);
    }

    public void notificationLogUpdate(JoinPoint joinPoint,
                                      Object result){
        log.debug("Updating Log record...");
    }

    // Before advice (logging before method execution)
    @Before("notificationServiceMethods()")
    public void logBefore(JoinPoint joinPoint){
        log.debug("About to send notification...");
    }

    // After Returning advice (logging successful execution)
    @AfterReturning(pointcut = "notificationServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint,
                                  Object result){
        log.debug("Notification sent successfully. Result: {}", result);
    }

    // After Throwing advice (logging exceptions)
    @AfterThrowing(pointcut = "notificationServiceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint,
                                 Exception exception){
        log.debug("Error occurred while sending notification. Exception: {}", exception.getMessage());
    }
}
