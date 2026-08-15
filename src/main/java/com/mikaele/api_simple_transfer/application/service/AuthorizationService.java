package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.infrastructure.client.AuthorizationClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public boolean validateAuth() {
        try {
            var response = authorizationClient.validateAuth();
            return response != null && response.data() != null && response.data().authorization();
        } catch (FeignException e) {
            return false;
        }
    }

}
