package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    @Query(value = "SELECT i FROM Invoice AS i" +
//            " WHERE ( :#{#invoice.invoiceDate} IS NULL OR i.invoiceDate <= :#{#invoice.invoiceDate} )" +
            " WHERE ( :#{#invoice.clientId} IS NULL OR i.clientId = :#{#invoice.clientId} )"
//            " AND ( :#{#invoice.state} IS NULL OR UPPER(i.state) = UPPER(:#{#invoice.state}) )"
    )
    Page<Invoice> paginationFilter(Pageable pageable, @Param("invoice") Invoice invoice);
}
