package com.mikaele.api_simple_transfer.infrastructure.repository;

import com.mikaele.api_simple_transfer.domain.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
