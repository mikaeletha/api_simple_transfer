package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.domain.entity.User;
import com.mikaele.api_simple_transfer.infrastructure.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User findUserById(Long id) {
        // TODO: criar exception
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found."));
    }
}
