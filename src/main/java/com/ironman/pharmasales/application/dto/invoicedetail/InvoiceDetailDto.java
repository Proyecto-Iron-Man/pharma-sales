package com.ironman.pharmasales.application.dto.invoicedetail;

import com.ironman.pharmasales.application.dto.product.ProductMediumDto;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class InvoiceDetailDto {
    private Integer quantity;
    private BigDecimal price;
    private ProductMediumDto product;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
