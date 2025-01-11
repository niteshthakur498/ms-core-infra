package com.nitesh.notificationservice.exception;

public class TemplateNotFoundException extends RuntimeException {

    // Constructor with message
    public TemplateNotFoundException(String message){
        super(message);  // Pass the message to the superclass (RuntimeException)
    }

    // Constructor with message and cause (used for chaining exceptions)
    public TemplateNotFoundException(String message,
                                     Throwable cause){
        super(message, cause);  // Pass the message and cause to the superclass
    }
}
