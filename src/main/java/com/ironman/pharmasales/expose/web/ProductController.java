package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.product.ProductDto;
import com.ironman.pharmasales.application.dto.product.ProductFilterDto;
import com.ironman.pharmasales.application.dto.product.ProductMediumDto;
import com.ironman.pharmasales.application.dto.product.ProductSaveDto;
import com.ironman.pharmasales.application.service.ProductService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.ironman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequestMapping("/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> products = productService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id)
            throws DataNotFoundException {
        ProductDto product = productService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductSaveDto productBody)
            throws DataNotFoundException {
        ProductDto product = productService.create(productBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(product);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> edit(@PathVariable("id") Long id, @Valid @RequestBody ProductSaveDto productBody)
            throws DataNotFoundException {
        ProductDto product = productService.edit(id, productBody);

        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> disabled(@PathVariable("id") Long id) throws DataNotFoundException {
        ProductDto product = productService.disabled(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/pagination-filter")
    public ResponseEntity<Page<ProductDto>> paginationFilter(Pageable pageable, Optional<ProductFilterDto> filter) {
        Page<ProductDto> productDtoPage = productService.paginationFilter(pageable, filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(productDtoPage);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/search/{searchText}")
    public ResponseEntity<List<ProductMediumDto>> search(@PathVariable("searchText") String searchText) {
        List<ProductMediumDto> products = productService.search(searchText);

        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }
}
