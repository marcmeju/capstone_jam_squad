package learn.notbooking.controllers;

import java.time.LocalDateTime;

public class GlobalErrorResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final String message;

    public GlobalErrorResponse(String message) {
        this.message = message;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }


}
