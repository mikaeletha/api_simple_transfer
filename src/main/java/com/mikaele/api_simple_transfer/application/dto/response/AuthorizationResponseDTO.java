package com.mikaele.api_simple_transfer.application.dto.response;

public record AuthorizationResponseDTO(
        String status,
        AuthorizationDataDto data
) {
}
