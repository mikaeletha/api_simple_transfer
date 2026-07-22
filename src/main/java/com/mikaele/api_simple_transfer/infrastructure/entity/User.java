package com.mikaele.api_simple_transfer.infrastructure.entity;

import com.mikaele.api_simple_transfer.infrastructure.enumeration.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String cpfCnpj;

    @Column(unique = true, nullable = false)
    private String email;

    // TODO: immplementar criptografia de senha
    @Column(nullable = false)
    private String password;

    private UserType userType;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;
}
