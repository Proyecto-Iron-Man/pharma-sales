package com.ironman.pharmasales.application.dto.subcategory;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubcategoryDto {
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private Long categoryId;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
