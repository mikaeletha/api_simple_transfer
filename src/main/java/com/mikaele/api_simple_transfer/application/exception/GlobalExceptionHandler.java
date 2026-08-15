package com.mikaele.api_simple_transfer.application.exception;

import com.mikaele.api_simple_transfer.application.exception.transfer.*;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MerchantNotAllowed.class)
    public ResponseEntity<Map<String, String>> handleMerchantNotAllowed(MerchantNotAllowed ex) {
        return ResponseEntity.status(403).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(InsufficientBalance.class)
    public ResponseEntity<Map<String, String>> handleInsufficientBalance(InsufficientBalance ex) {
        return ResponseEntity.status(400).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(TransferUnauthorized.class)
    public ResponseEntity<Map<String, String>> handleTransferUnauthorized(TransferUnauthorized ex) {
        return ResponseEntity.status(403).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(NotificationFailed.class)
    public ResponseEntity<Map<String, String>> handleNotificationFailed(NotificationFailed ex) {
        return ResponseEntity.status(504).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Map<String, String>> handleFeignException(FeignException ex) {
        return ResponseEntity.status(503).body(Map.of("message", "External service is currently unavailable. Please try again later."));
    }
}