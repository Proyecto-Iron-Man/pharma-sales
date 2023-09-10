package com.ironman.pharmasales.application.dto.category;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CategorySaveDto {
    private String name;
    private String description;
}
