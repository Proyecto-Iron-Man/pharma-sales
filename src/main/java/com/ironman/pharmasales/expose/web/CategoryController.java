package com.ironman.pharmasales.expose.web;


import com.ironman.pharmasales.application.dto.category.CategoryDto;
import com.ironman.pharmasales.application.dto.category.CategorySaveDto;
import com.ironman.pharmasales.application.dto.category.CategorySimpleDto;
import com.ironman.pharmasales.application.service.CategoryService;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    ResponseEntity<List<CategoryDto>> findAll() {
        List<CategoryDto> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        CategoryDto category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    ResponseEntity<CategoryDto> create(@RequestBody CategorySaveDto categoryBody) {
        CategoryDto category = categoryService.create(categoryBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(category);
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> edit(@PathVariable("id") Long id, @RequestBody CategorySaveDto categoryBody) {
        CategoryDto category = categoryService.edit(id, categoryBody);

        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    ResponseEntity<CategoryDto> disabled(@PathVariable("id") Long id) {
        CategoryDto category = categoryService.disbled(id);

        return ResponseEntity.ok(category);
    }

    @GetMapping("/select")
    ResponseEntity<List<CategorySimpleDto>> select() {
        List<CategorySimpleDto> categories = categoryService.select();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/search-by-state/{state}")
    ResponseEntity<List<CategorySimpleDto>> searchByState(@PathVariable("state") String state) {
        List<CategorySimpleDto> categories = categoryService.searchByState(state);

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/pagination")
    ResponseEntity<Page<CategoryDto>> pagination(Pageable pageable) {
        Page<CategoryDto> categoryPage = categoryService.pagination(pageable);

        return ResponseEntity.ok(categoryPage);
    }

}
