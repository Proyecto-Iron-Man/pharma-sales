package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.application.dto.category.CategorySimpleDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto create(CategorySaveDto categoryBody);
    CategoryDto edit(Long id, CategorySaveDto categoryBody);
    CategoryDto disbled(Long id);

    List<CategorySimpleDto> select();
}
