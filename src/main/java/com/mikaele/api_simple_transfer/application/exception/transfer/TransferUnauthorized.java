package com.mikaele.api_simple_transfer.application.exception.transfer;

public class TransferUnauthorized extends RuntimeException {
    public TransferUnauthorized(String message) {
        super(message);
    }
}
