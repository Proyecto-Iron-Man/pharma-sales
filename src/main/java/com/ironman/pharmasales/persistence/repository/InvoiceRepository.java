package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
