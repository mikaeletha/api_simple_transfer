package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.application.dto.request.TransferRequestDTO;
import com.mikaele.api_simple_transfer.application.exception.transfer.NotificationFailed;
import com.mikaele.api_simple_transfer.application.exception.transfer.TransferUnauthorized;
import com.mikaele.api_simple_transfer.domain.entity.Transfer;
import com.mikaele.api_simple_transfer.domain.entity.User;
import com.mikaele.api_simple_transfer.infrastructure.repository.TransferRepository;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferManagementService {

    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final TransferRepository transferRepository;
    private final NotificationService notificationService;

    @Transactional
    public void executeTransfer(TransferRequestDTO dto) {
        User payer = userService.findUserById(dto.payer());
        User payee = userService.findUserById(dto.payee());

        // executa o débito/crédito (validacao feita no dominio)
        payer.getWallet().debit(dto.value());
        payee.getWallet().credit(dto.value());

        // servico externo de autorizacao (simulado) 
        validateTransferAuthorization();

        // salvar transacao no banco de dados
        Transfer transfer = Transfer.builder()
                .amount(dto.value())
                .payer(payer)
                .payee(payee)
                .build();

        transferRepository.save(transfer);

        // enviar notificação
        sendNotification();
    }

    private void validateTransferAuthorization() {
        if (!authorizationService.validateAuth()) {
            throw new TransferUnauthorized("Transfer authorization was denied by the authorization service.");
        }
    }

    private void sendNotification() {
        try {
            notificationService.sendNotification();
        } catch (FeignException e) {
            throw new NotificationFailed("Failed to send transfer notification.");
        }
    }
}