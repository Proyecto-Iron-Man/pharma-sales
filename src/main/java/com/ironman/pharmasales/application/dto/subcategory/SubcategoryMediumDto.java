package com.ironman.pharmasales.application.dto.subcategory;

import com.ironman.pharmasales.application.dto.category.CategorySimpleDto;
import lombok.Data;

@Data
public class SubcategoryMediumDto {
    private Long id;
    private String name;
    private CategorySimpleDto category;
}
