package com.mikaele.api_simple_transfer.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfers")
@Builder
public class Transfer extends BaseEntity{

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "payer_id", nullable = false)
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id", nullable = false)
    private User payee;

    public Transfer(User payer, User payee, BigDecimal amount) {
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
    }
}
