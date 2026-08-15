package com.mikaele.api_simple_transfer.application.exception.transfer;

public class InsufficientBalance extends RuntimeException {
    public InsufficientBalance(String message) {
        super(message);
    }
}
