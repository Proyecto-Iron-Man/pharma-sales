package com.ironman.pharmasales.application.dto.subcategory.mapper;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.persistence.entity.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubcategoryMapper {
    // Dto from Entity Start

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "categoryId",  source = "categoryId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);
    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
    // Dto from Entity End


}
