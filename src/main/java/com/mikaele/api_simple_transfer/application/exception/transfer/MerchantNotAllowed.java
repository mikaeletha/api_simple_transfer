package com.mikaele.api_simple_transfer.application.exception.transfer;

public class MerchantNotAllowed extends RuntimeException {
    public MerchantNotAllowed(String message) {
        super(message);
    }
}
