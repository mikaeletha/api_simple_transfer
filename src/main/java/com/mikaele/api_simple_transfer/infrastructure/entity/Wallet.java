package com.mikaele.api_simple_transfer.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallets")
public class Wallet extends BaseEntity {

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
