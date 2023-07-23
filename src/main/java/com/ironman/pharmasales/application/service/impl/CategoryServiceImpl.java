package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.service.CategoryService;
import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return categories;
    }

    @Override
    public Category findById(Long id) {
        Category category = categoryRepository.findById(id).get();

        return category;
    }

    @Override
    public Category create(Category categoryBody) {
        Category category = categoryRepository.save(categoryBody);

        return category;
    }

    @Override
    public Category edit(Long id, Category categoryBody) {
        categoryBody.setId(id);

        Category category = categoryRepository.save(categoryBody);

        return category;
    }

    @Override
    public Category disbled(Long id) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setState("E");


        Category category = categoryRepository.save(categoryDb);

        return category;
    }
}
