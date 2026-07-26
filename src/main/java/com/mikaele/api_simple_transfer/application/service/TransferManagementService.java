package com.mikaele.api_simple_transfer.application.service;

import com.mikaele.api_simple_transfer.application.dto.request.TransferRequestDTO;
import com.mikaele.api_simple_transfer.domain.entity.User;
import com.mikaele.api_simple_transfer.domain.enumeration.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransferManagementService {
    private final UserService userService;

    public void executeTransfer(TransferRequestDTO transferRequest) {
        User payer = userService.findUserById(transferRequest.payer());
        User payee = userService.findUserById(transferRequest.payee());

        validatePayer(payer);
        validatePayerBalance(payer, transferRequest.value());

    }

    private void validatePayer(User user) {
        if (user.getUserType().equals(UserType.MERCHANT)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Merchants are not allowed to initiate transfers.");
        }
    }

    private void validatePayerBalance(User payer, BigDecimal amount){
        if(payer.getWallet().getBalance().compareTo(amount) < 0){
            // throw new IllegalStateException("Insufficient balance to complete the transfer.");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Insufficient balance to complete the transfer."
            );
        }
    }
}
