package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.ironman.pharmasales.application.service.SubcategoryService;
import com.ironman.pharmasales.persistence.entity.Subcategory;
import com.ironman.pharmasales.persistence.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;

    @Override
    public List<SubcategoryDto> findAll() {
        List<Subcategory> subcategories = (List<Subcategory>) subcategoryRepository.findAll();

        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public SubcategoryDto findById(Long id) {

        Subcategory subcategory = subcategoryRepository.findById(id).get();

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }
}
