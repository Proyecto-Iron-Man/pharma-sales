package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

    @Query(value = "SELECT s FROM Subcategory s" +
            " WHERE (:#{#subcategory.name} IS NULL OR UPPER(s.name) LIKE UPPER(CONCAT('%',:#{#subcategory.name},'%')))" +
            " AND (:#{#subcategory.description} IS NULL OR UPPER(s.description) LIKE UPPER(CONCAT('%',:#{#subcategory.description},'%')))" +
            " AND (:#{#subcategory.categoryId} IS NULL OR s.categoryId = :#{#subcategory.categoryId})" +
            " AND (:#{#subcategory.state} IS NULL OR UPPER(s.state) = UPPER(:#{#subcategory.state}))"
    )
    List<Subcategory> filter(@Param("subcategory") Subcategory subcategory);

    @Query(value = "SELECT s FROM Subcategory AS s" +
            " WHERE (:#{#subcategory.name} IS NULL OR UPPER(s.name) LIKE UPPER(CONCAT('%',:#{#subcategory.name},'%')) )" +
            " AND (:#{#subcategory.description} IS NULL OR UPPER(s.description) LIKE UPPER(CONCAT('%',:#{#subcategory.description},'%')) )" +
            " AND (:#{#subcategory.categoryId} IS NULL OR s.categoryId = :#{#subcategory.categoryId})" +
            " AND (:#{#subcategory.state} IS NULL OR UPPER(s.state) = UPPER(:#{#subcategory.state}) )"
    )
    Page<Subcategory> paginationFilter(Pageable pageable, @Param("subcategory") Subcategory subcategory);
}
