package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryDto> findAll();

    SubcategoryDto findById(Long id);

}