package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.ironman.pharmasales.application.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<SubcategoryDto> create(@RequestBody SubcategorySaveDto subcategoryBody) {
        SubcategoryDto subcategory = subcategoryService.create(subcategoryBody);

        return ResponseEntity.ok(subcategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDto> edit(@PathVariable("id") Long id, @RequestBody SubcategorySaveDto subcategoryBody) {
        SubcategoryDto subcategory = subcategoryService.edit(id, subcategoryBody);

        return ResponseEntity.ok(subcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubcategoryDto> disabled(@PathVariable("id") Long id) {
        SubcategoryDto subcategory = subcategoryService.disabled(id);

        return ResponseEntity.ok(subcategory);
    }

}
