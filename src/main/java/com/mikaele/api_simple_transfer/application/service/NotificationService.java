package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.infrastructure.client.AuthorizationClient;
import com.mikaele.api_simple_transfer.infrastructure.client.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private  final NotificationClient client;

    public void sendNotification(){
        client.sendNotification();
    }

}
