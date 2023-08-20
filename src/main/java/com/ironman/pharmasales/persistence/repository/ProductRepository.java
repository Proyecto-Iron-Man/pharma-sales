package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query(value = "SELECT p FROM Product AS p" +
            " WHERE ( :#{#product.name} IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%',:#{#product.name},'%')) )" +
            " AND ( :#{#product.description} IS NULL OR UPPER(p.description) LIKE UPPER(CONCAT('%',:#{#product.description},'%')) )" +
            " AND ( :#{#product.stock} IS NULL OR p.stock <= :#{#product.stock} )" +
            " AND ( :#{#product.subcategoryId} IS NULL OR p.subcategoryId = :#{#product.subcategoryId} )" +
            " AND ( :#{#product.state} IS NULL OR UPPER(p.state) = UPPER(:#{#product.state}) )"
    )
    Page<Product> paginationFilter(Pageable pageable, @Param("product") Product product);

    @Query(value = "SELECT p FROM Product AS p" +
            " WHERE CONCAT(UPPER(p.name), ' ', UPPER(p.presentation)) LIKE UPPER(CONCAT('%',:searchText,'%'))" +
            " OR p.subcategoryId IN ( SELECT s.id FROM Subcategory AS s WHERE UPPER(s.name) LIKE UPPER(CONCAT('%',:searchText,'%')) )" +
            " AND p.state = 'A'"
    )
    List<Product> search(@Param("searchText") String searchText);
}
