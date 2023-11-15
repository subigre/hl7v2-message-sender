package com.subiger.hl7v2.messagesender.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * @author Renaud Subiger
 * @since 1.0
 */
public class MessageRequest {

    @Pattern(regexp = "[^:]+:\\d{2,5}", message = "Destination is not valid")
    private String destination;

    @NotBlank(message = "Message is required")
    private String message;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "destination='" + destination + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
