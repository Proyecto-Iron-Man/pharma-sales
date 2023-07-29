package com.ironman.pharmasales.application.dto.subcategory.mapper;

import com.ironman.pharmasales.application.dto.category.mapper.CategoryMapper;
import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.persistence.entity.Subcategory;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {CategoryMapper.class, StateMapper.class}

)
public interface SubcategoryMapper {
    // Dto from Entity Start

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);
    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
    // Dto from Entity End


}
