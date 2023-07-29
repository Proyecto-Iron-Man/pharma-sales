package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.application.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAll() {
        List<SubcategoryDto> subcategories = subcategoryService.findAll();

        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDto> findById(@PathVariable("id") Long id) {
        SubcategoryDto subcategory = subcategoryService.findById(id);

        return ResponseEntity.ok(subcategory);
    }

}
