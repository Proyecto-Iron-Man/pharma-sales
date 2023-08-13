package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.DocumentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long> {
    List<DocumentType> findByState(String state);

    @Query(value = "SELECT d FROM DocumentType AS d" +
            " WHERE ( :#{#documentType.name} IS NULL OR UPPER(d.name) LIKE UPPER(CONCAT('%',:#{#documentType.name},'%')) )" +
            " AND ( :#{#documentType.description} IS NULL OR UPPER(d.description) LIKE UPPER(CONCAT('%',:#{#documentType.description},'%')) )" +
            " AND ( :#{#documentType.sunatCode} IS NULL OR UPPER(d.sunatCode) LIKE UPPER(CONCAT('%',:#{#documentType.sunatCode},'%')) )" +
            " AND ( :#{#documentType.size} IS NULL OR d.size = :#{#documentType.size} )" +
            " AND ( :#{#documentType.isSizeExact} IS NULL OR d.isSizeExact = :#{#documentType.isSizeExact} )" +
            " AND ( :#{#documentType.isNumeric} IS NULL OR d.isNumeric = :#{#documentType.isNumeric} )" +
            " AND ( :#{#documentType.state} IS NULL OR UPPER(d.state) = UPPER(:#{#documentType.state}) )"
    )
    Page<DocumentType> paginationFilter(Pageable pageable, @Param("documentType") DocumentType documentType);
}
