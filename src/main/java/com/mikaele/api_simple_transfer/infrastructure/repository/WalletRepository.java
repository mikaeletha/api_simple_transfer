package com.mikaele.api_simple_transfer.infrastructure.repository;

import com.mikaele.api_simple_transfer.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
