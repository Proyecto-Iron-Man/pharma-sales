package com.ironman.pharmasales.application.dto.category;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
