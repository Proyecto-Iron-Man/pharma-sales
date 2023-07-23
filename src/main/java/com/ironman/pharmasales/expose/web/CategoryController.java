package com.ironman.pharmasales.expose.web;


import com.ironman.pharmasales.application.service.CategoryService;
import com.ironman.pharmasales.persistence.entity.Category;
import com.ironman.pharmasales.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<Category>> findAll() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> findById(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    ResponseEntity<Category> create(@RequestBody Category categoryBody) {
        Category category = categoryRepository.save(categoryBody);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> edit(@PathVariable("id") Long id, @RequestBody Category categoryBody) {
        categoryBody.setId(id);

        Category category = categoryRepository.save(categoryBody);


        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Category> disabled(@PathVariable("id") Long id) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setState("E");


        Category category = categoryRepository.save(categoryDb);

        return ResponseEntity.ok(category);
    }

}
