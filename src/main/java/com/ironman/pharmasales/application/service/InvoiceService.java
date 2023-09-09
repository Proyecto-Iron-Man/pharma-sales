package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceFilterDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {

    List<InvoiceDto> findAll();

    InvoiceDto findById(Long id) throws DataNotFoundException;

    InvoiceDto create(InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException;

    InvoiceDto edit(Long id, InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException;

    InvoiceDto disabled(Long id) throws DataNotFoundException;

    Page<InvoiceDto> paginationFilter(Pageable pageable, Optional<InvoiceFilterDto> filter);
}
