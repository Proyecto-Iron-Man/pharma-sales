package com.ironman.pharmasales.application.dto.category.mapper;

import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.application.dto.category.CategorySimpleDto;
import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class})
public interface CategoryMapper {
    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDtos(List<Category> categories);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategorySimpleDto toCategorySimpleDto(Category category);
    List<CategorySimpleDto> toCategorySimpleDtos(List<Category> categories);
    // Dto from Entity End

    // Entity from Dto Start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toCategory(CategorySaveDto categorySaveDto);

    // Entity from Dto End
}
