package com.mikaele.api_simple_transfer.application.exception.transfer;

public class NotificationFailed extends RuntimeException {
    public NotificationFailed(String message) {
        super(message);
    }
}
