package com.mikaele.api_simple_transfer.application.controller;

import com.mikaele.api_simple_transfer.application.dto.request.TransferRequestDTO;
import com.mikaele.api_simple_transfer.application.dto.response.TransferResponseDTO;
import com.mikaele.api_simple_transfer.application.service.TransferManagementService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class TransferManagerController {

    private final TransferManagementService transferManagementService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> realizeTransfer(@RequestBody TransferRequestDTO transferRequestDTO) {
        transferManagementService.executeTransfer(transferRequestDTO);
        return ResponseEntity.ok(new TransferResponseDTO("Transfer completed successfully."));
    }
}
