package com.ironman.pharmasales.application.dto.invoice;

import com.ironman.pharmasales.application.dto.invoicedetail.InvoiceDetailSaveDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceSaveDto {
    private LocalDateTime invoiceDate;

    @NotNull(message = "El campo cliente es requerido")
    @Positive(message = "El campo cliente debe ser un numero positivo")
    private Long clientId;

    @NotNull(message = "El campo usuario es requerido")
    @Positive(message = "El campo usuario debe ser un numero positivo")
    private Long userId;

    @Valid
    private List<InvoiceDetailSaveDto> invoiceDetails;
}
