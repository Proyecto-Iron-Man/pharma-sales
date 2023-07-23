package com.ironman.pharmasales.application.dto.category.mapper;

import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
    // Entity to Dto Start

    // Entity to Dto End
    /*
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
     */

    // Dto to Entity Start
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toCategory(CategorySaveDto categorySaveDto);

    // Dto to Entity End
}
