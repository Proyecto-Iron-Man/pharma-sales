package com.ironman.pharmasales.expose.web;


import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    ResponseEntity<List<Category>> findAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return ResponseEntity.ok(categories);
    }


}
