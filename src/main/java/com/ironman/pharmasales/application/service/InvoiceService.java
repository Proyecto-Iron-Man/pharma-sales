package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;

public interface InvoiceService {

    InvoiceDto findById(Long id) throws DataNotFoundException;

    InvoiceDto create(InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException;

    InvoiceDto edit(Long id, InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException;
}
