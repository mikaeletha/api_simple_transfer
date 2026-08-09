package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.domain.entity.User;
import com.mikaele.api_simple_transfer.domain.entity.Wallet;
import com.mikaele.api_simple_transfer.infrastructure.repository.UserRepository;
import com.mikaele.api_simple_transfer.infrastructure.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final WalletRepository repository;

    public void save(Wallet wallet) {
        // TODO: criar exception
        repository.save(wallet);
    }
}
