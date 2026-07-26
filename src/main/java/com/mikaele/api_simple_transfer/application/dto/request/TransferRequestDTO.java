package com.mikaele.api_simple_transfer.application.dto.request;

import java.math.BigDecimal;

public record TransferRequestDTO(
        BigDecimal value,
        Long payer,
        Long payee
) {
}
