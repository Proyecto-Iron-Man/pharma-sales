package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.product.ProductDto;
import com.ironman.pharmasales.application.dto.product.ProductFilterDto;
import com.ironman.pharmasales.application.dto.product.ProductMediumDto;
import com.ironman.pharmasales.application.dto.product.ProductSaveDto;
import com.ironman.pharmasales.application.dto.product.mapper.ProductMapper;
import com.ironman.pharmasales.application.service.ProductService;
import com.ironman.pharmasales.persistence.entity.Product;
import com.ironman.pharmasales.persistence.entity.Subcategory;
import com.ironman.pharmasales.persistence.repository.ProductRepository;
import com.ironman.pharmasales.persistence.repository.SubcategoryRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = (List<Product>) productRepository.findAll();

        return productMapper.toProductDtos(products);
    }

    @Override
    public ProductDto findById(Long id) throws DataNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Producto no encontrado para el id: " + id));

        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto create(ProductSaveDto productBody) throws DataNotFoundException {
        Subcategory subcategory = subcategoryRepository.findById(productBody.getSubcategoryId())
                .orElseThrow(() -> new DataNotFoundException("Subcategoria no encontrado para el id: " + productBody.getSubcategoryId()));


        Product productSave = productMapper.toProduct(productBody);
        productSave.setSubcategory(subcategory);
        productSave.setState(State.ACTIVE.getValue());
        productSave.setCreatedAt(LocalDateTime.now());

        Product product = productRepository.save(productSave);

        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto edit(Long id, ProductSaveDto productBody) throws DataNotFoundException {
        Subcategory subcategory = subcategoryRepository.findById(productBody.getSubcategoryId())
                .orElseThrow(() -> new DataNotFoundException("Subcategoria no encontrado para el id: " + productBody.getSubcategoryId()));

        Product productDb = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Producto no encontrado para el id: " + id));

        productMapper.updateProduct(productDb, productBody);
        productDb.setSubcategory(subcategory);
        productDb.setUpdatedAt(LocalDateTime.now());

        Product product = productRepository.save(productDb);

        return productMapper.toProductDto(product);
    }

    @Override
    public ProductDto disabled(Long id) throws DataNotFoundException {
        Product productDb = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Producto no encontrado para el id: " + id));

        productDb.setState(State.DISABLE.getValue());

        Product product = productRepository.save(productDb);

        return productMapper.toProductDto(product);
    }

    @Override
    public Page<ProductDto> paginationFilter(Pageable pageable, Optional<ProductFilterDto> filter) {
        ProductFilterDto filterDto = filter.orElse(new ProductFilterDto());

        Product product = productMapper.toProduct(filterDto);

        Page<Product> productPage = productRepository.paginationFilter(pageable, product);

        return new PageImpl<>(
                productMapper.toProductDtos(productPage.getContent()),
                productPage.getPageable(),
                productPage.getTotalElements()
        );
    }

    @Override
    public List<ProductMediumDto> search(String searchText) {
        List<Product> products = productRepository.search(searchText);

        return productMapper.toProductMediumDtos(products);
    }
}

