package com.ironman.pharmasales.expose.web;


import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    ResponseEntity<List<Category>> findAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        Category category = categoryRepository.findById(id).get();

        return ResponseEntity.ok(category);
    }
}
