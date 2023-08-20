package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.dto.invoice.mapper.InvoiceMapper;
import com.ironman.pharmasales.application.service.InvoiceService;
import com.ironman.pharmasales.persistence.entity.Invoice;
import com.ironman.pharmasales.persistence.repository.InvoiceRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;


    @Override
    public InvoiceDto create(InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException {

        Invoice invoiceSave = invoiceMapper.toInvoice(invoiceSaveDto);

        invoiceSave.setState(State.ACTIVE.getValue());
        invoiceSave.setCreatedAt(LocalDateTime.now());

        invoiceSave.getInvoiceDetails().forEach(detail ->{
            detail.setInvoice(invoiceSave);
            detail.setState(State.ACTIVE.getValue());
            detail.setCreatedAt(LocalDateTime.now());
        });

        Invoice invoice = invoiceRepository.save(invoiceSave);

        return invoiceMapper.toInvoiceDto(invoice);
    }
}
