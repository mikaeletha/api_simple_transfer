package com.mikaele.api_simple_transfer.infrastructure.repository;

import com.mikaele.api_simple_transfer.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
