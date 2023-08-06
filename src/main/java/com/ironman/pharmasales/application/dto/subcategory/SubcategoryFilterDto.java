package com.ironman.pharmasales.application.dto.subcategory;

import lombok.Data;

@Data
public class SubcategoryFilterDto {
    private String name;
    private String description;
    private Long categoryId;
    private String state;
}
