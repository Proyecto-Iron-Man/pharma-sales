package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {

    List<Category> findByState(String state);

    @Query(
            value = "SELECT c FROM Category c" +
                    " WHERE c.state = :state"
    )
    List<Category> searchByState(@Param("state") String state);
}
