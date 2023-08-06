package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.ironman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.ironman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.ironman.pharmasales.application.service.SubcategoryService;
import com.ironman.pharmasales.persistence.entity.Subcategory;
import com.ironman.pharmasales.persistence.repository.SubcategoryRepository;
import com.ironman.pharmasales.shared.state.enums.State;
import com.ironman.pharmasales.shared.string.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Override
    public SubcategoryDto create(SubcategorySaveDto subcategoryBody) {
        Subcategory subcategorySave = subcategoryMapper.toSubcategory(subcategoryBody);

        subcategorySave.setKeyword(new StringHelper().slugsKeywords(subcategoryBody.getName()));
        subcategorySave.setState(State.ACTIVE.getValue());
        subcategorySave.setCreatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategorySave);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto edit(Long id, SubcategorySaveDto subcategoryBody) {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).get();

        subcategoryMapper.updateSubcategory(subcategoryDb, subcategoryBody);

        subcategoryDb.setKeyword(new StringHelper().slugsKeywords(subcategoryBody.getName()));
        subcategoryDb.setUpdatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategoryDb);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto disabled(Long id) {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).get();
        subcategoryDb.setState(State.DISABLE.getValue());

        Subcategory subcategory = subcategoryRepository.save(subcategoryDb);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public List<SubcategoryDto> filter(Optional<SubcategoryFilterDto> filter) {

        SubcategoryFilterDto filterDto = filter
                .orElse(new SubcategoryFilterDto());

        Subcategory subcategory = subcategoryMapper.toSubcategory(filterDto);

        List<Subcategory> subcategories = subcategoryRepository.filter(subcategory);

        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public Page<SubcategoryDto> paginationFilter(Pageable pageable, Optional<SubcategoryFilterDto> filter) {
        SubcategoryFilterDto filterDto = filter.orElse(new SubcategoryFilterDto());

        Subcategory subcategory = subcategoryMapper.toSubcategory(filterDto);

        Page<Subcategory> subcategoryPage = subcategoryRepository.paginationFilter(pageable, subcategory);

        List<SubcategoryDto> subcategoryDtos = subcategoryMapper.toSubcategoryDtos(subcategoryPage.getContent());

        Page<SubcategoryDto> subcategoryDtoPage = new PageImpl<>(
                subcategoryDtos,
                subcategoryPage.getPageable(),
                subcategoryPage.getTotalElements()
        );

        return subcategoryDtoPage;
    }
}
