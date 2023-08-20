package com.ironman.pharmasales.application.dto.invoice;

import com.ironman.pharmasales.application.dto.client.ClientMediumDto;
import com.ironman.pharmasales.application.dto.invoicedetail.InvoiceDetailDto;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
    private Long id;
    private LocalDateTime invoiceDate;
    private ClientMediumDto client;
    private Long userId;
    private List<InvoiceDetailDto> invoiceDetails;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
