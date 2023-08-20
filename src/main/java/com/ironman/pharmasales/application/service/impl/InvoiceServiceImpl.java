package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.dto.invoice.mapper.InvoiceMapper;
import com.ironman.pharmasales.application.service.InvoiceService;
import com.ironman.pharmasales.persistence.entity.Invoice;
import com.ironman.pharmasales.persistence.repository.InvoiceDetailRepository;
import com.ironman.pharmasales.persistence.repository.InvoiceRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final InvoiceMapper invoiceMapper;


    @Override
    public InvoiceDto findById(Long id) throws DataNotFoundException {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Venta no encontrado para el id: " + id));

        return invoiceMapper.toInvoiceDto(invoice);
    }

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

    @Transactional
    @Override
    public InvoiceDto edit(Long id, InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException {
        Invoice invoiceDb = invoiceRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Venta no encontrado para el id: " + id));

        invoiceMapper.updateInvoice(invoiceDb, invoiceSaveDto);

        invoiceDb.setUpdatedAt(LocalDateTime.now());

        invoiceDb.getInvoiceDetails().forEach(detail ->{
            detail.getId().setInvoiceId(id);
            detail.setInvoice(invoiceDb);
            detail.setState(State.ACTIVE.getValue());
            detail.setCreatedAt(LocalDateTime.now());
            detail.setUpdatedAt(LocalDateTime.now());
        });

        invoiceDetailRepository.saveAll(invoiceDb.getInvoiceDetails());

        Invoice invoice = invoiceRepository.save(invoiceDb);

        return invoiceMapper.toInvoiceDto(invoice);
    }
}
