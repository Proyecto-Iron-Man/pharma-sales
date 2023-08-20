package com.ironman.pharmasales.application.dto.product;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryMediumDto;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private String presentation;
    private String unitMeasure;
    private String prescription;
    private String precaution;
    private String sideEffect;
    private BigDecimal price;
    private Long stock;
    private SubcategoryMediumDto subcategory;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
