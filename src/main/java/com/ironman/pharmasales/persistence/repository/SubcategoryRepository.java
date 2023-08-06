package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Subcategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

    @Query(value = "SELECT s FROM Subcategory s" +
            " WHERE (:#{#subcategory.name} IS NULL OR s.name LIKE %:#{#subcategory.name}%)" +
            " AND (:#{#subcategory.description} IS NULL OR s.description LIKE %:#{#subcategory.description}%)" +
            " AND (:#{#subcategory.categoryId}) IS NULL OR s.categoryId = :#{#subcategory.categoryId}" +
            " AND (:#{#subcategory.state}) IS NULL OR s.state = :#{#subcategory.state}"
    )
    List<Subcategory> filter(@Param("subcategory") Subcategory subcategory);
}
