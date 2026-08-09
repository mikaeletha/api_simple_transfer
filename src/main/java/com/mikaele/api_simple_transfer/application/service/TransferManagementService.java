package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.application.dto.request.TransferRequestDTO;
import com.mikaele.api_simple_transfer.domain.entity.Transfer;
import com.mikaele.api_simple_transfer.domain.entity.User;
import com.mikaele.api_simple_transfer.domain.entity.Wallet;
import com.mikaele.api_simple_transfer.domain.enumeration.UserType;
import com.mikaele.api_simple_transfer.infrastructure.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferManagementService {
    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final WalletService walletService;
    private final TransferRepository transferRepository;
    private final NotificationService notificationService;

    @Transactional
    public void executeTransfer(TransferRequestDTO transferRequestDTO) {
        User payer = userService.findUserById(transferRequestDTO.payer());
        User payee = userService.findUserById(transferRequestDTO.payee());

        validatePayer(payer);
        validatePayerBalance(payer, transferRequestDTO.value());
        validateTransferAuthorization();

        // atualizar saldo da carteira pagador
        payer.getWallet().setBalance(payer.getWallet().getBalance().subtract(transferRequestDTO.value()));
        updateWalletBalance(payer.getWallet());

        // atualizar saldo da carteira recebedor
        payee.getWallet().setBalance(payee.getWallet().getBalance().add(transferRequestDTO.value()));
        updateWalletBalance(payee.getWallet());

        // salvar transação
        Transfer transfer = Transfer.builder()
                .value(transferRequestDTO.value())
                .payer(payer)
                .payee(payee)
                .build();
        transferRepository.save(transfer);

        // enviar notificação
        sendNotification();

    }

    private void validatePayer(User user) {
        if (user.getUserType().equals(UserType.MERCHANT)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Merchants are not allowed to initiate transfers.");
        }
    }

    private void validatePayerBalance(User payer, BigDecimal amount) {
        if (payer.getWallet().getBalance().compareTo(amount) < 0) {
            // throw new IllegalStateException("Insufficient balance to complete the transfer.");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Insufficient balance to complete the transfer."
            );
        }
    }

    private void validateTransferAuthorization() {
        if (!authorizationService.validateAuth()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Transfer authorization was denied by the authorization service."
            );
        }
    }

    private void updateWalletBalance(Wallet wallet) {
        walletService.save(wallet);
    }

    private void sendNotification() {
        try {
            notificationService.sendNotification();
        } catch (HttpClientErrorException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY, "Failed to send transfer notification."
            );
        }
    }

}
