package com.mikaele.api_simple_transfer.infrastructure.client;

import com.mikaele.api_simple_transfer.application.dto.response.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "${authorization.url}", name = "auth")
public interface AuthorizationClient {
    @GetMapping
    AuthorizationResponseDTO validateAuth();
}