package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.infrastructure.client.AuthorizationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private  final AuthorizationClient client;

    public boolean validateAuth(){
        return client.validateAuth().data().authorization();
    }
}
