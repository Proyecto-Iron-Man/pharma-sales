package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.ironman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.ironman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.ironman.pharmasales.application.service.SubcategoryService;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subcategories")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAll() {
        List<SubcategoryDto> subcategories = subcategoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
    }

    @PostMapping
    public ResponseEntity<SubcategoryDto> create(@Valid @RequestBody SubcategorySaveDto subcategoryBody)
            throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.create(subcategoryBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subcategory);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDto> edit(
            @PathVariable("id") Long id,
            @Valid @RequestBody SubcategorySaveDto subcategoryBody
    ) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.edit(id, subcategoryBody);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubcategoryDto> disabled(@PathVariable("id") Long id) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.disabled(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SubcategoryDto>> filter(Optional<SubcategoryFilterDto> filter) {

        List<SubcategoryDto> subcategories = subcategoryService.filter(filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategories);
    }

    @GetMapping("/pagination-filter")
    public ResponseEntity<Page<SubcategoryDto>> paginationFilter(Pageable pageable, Optional<SubcategoryFilterDto> filter) {
        Page<SubcategoryDto> subcategoryDtoPage = subcategoryService.paginationFilter(pageable, filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategoryDtoPage);
    }

}
