package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    Category create(CategorySaveDto categoryBody);
    Category edit(Long id, CategorySaveDto categoryBody);
    Category disbled(Long id);
}
