package com.mikaele.api_simple_transfer.infrastructure.client;

import com.mikaele.api_simple_transfer.application.dto.response.AuthorizationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

//TODO: colocar url no application.properties
@FeignClient(url = "https://util.devi.tools/api/v1/notify", name = "notification")
public interface NotificationClient {

    @PostMapping
    void sendNotification();
}
