package com.ironman.pharmasales.persistence.repository;

import com.ironman.pharmasales.persistence.entity.InvoiceDetail;
import com.ironman.pharmasales.persistence.entity.InvoiceDetailPk;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceDetailRepository extends CrudRepository<InvoiceDetail, InvoiceDetailPk> {
}
