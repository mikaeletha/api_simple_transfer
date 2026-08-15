package com.mikaele.api_simple_transfer.domain.entity;

import com.mikaele.api_simple_transfer.domain.exception.InsufficientBalanceException;
import com.mikaele.api_simple_transfer.domain.exception.MerchantNotAllowedException;
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

    public void debit(BigDecimal value) {
        validatePayerType();
        validateBalance(value);
        this.balance = this.balance.subtract(value);
    }

    public void credit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    private void validatePayerType() {
        if (this.user.isMerchant()) {
            throw new MerchantNotAllowedException("Merchants are not allowed to make transfers.");
        }
    }

    private void validateBalance(BigDecimal value) {
        if (this.balance.compareTo(value) < 0) {
            throw new InsufficientBalanceException("Insufficient balance to perform the transfer.");
        }
    }
}