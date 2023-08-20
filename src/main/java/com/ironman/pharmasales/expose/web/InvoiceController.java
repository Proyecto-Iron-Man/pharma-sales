package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.service.InvoiceService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;


    @ApiResponse(responseCode = StatusCode.CREATED)
    @PostMapping
    public ResponseEntity<InvoiceDto> create(@Valid @RequestBody InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException {
        InvoiceDto invoice = invoiceService.create(invoiceSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoice);
    }

}
