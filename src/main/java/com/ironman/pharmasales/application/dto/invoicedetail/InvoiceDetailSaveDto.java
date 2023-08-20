package com.ironman.pharmasales.application.dto.invoicedetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDetailSaveDto {
    @NotNull(message = "El campo producto es requerido")
    @Positive(message = "El campo producto debe ser un numero positivo")
    private Long productId;

    @NotNull(message = "El campo cantidad es requerido")
    @Positive(message = "El campo cantidad debe ser un numero positivo")
    private Integer quantity;

    @NotNull(message = "El campo precio es requerido")
    @PositiveOrZero
    private BigDecimal price;
}
