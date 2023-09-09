package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceFilterDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.service.InvoiceService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<InvoiceDto>> findAll() {
        List<InvoiceDto> invoices = invoiceService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoices);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        InvoiceDto invoice = invoiceService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoice);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @PostMapping
    public ResponseEntity<InvoiceDto> create(@Valid @RequestBody InvoiceSaveDto invoiceSaveDto) throws DataNotFoundException {
        InvoiceDto invoice = invoiceService.create(invoiceSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoice);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> edit(@PathVariable("id") Long id, @Valid @RequestBody InvoiceSaveDto invoiceSaveDto)
            throws DataNotFoundException {
        InvoiceDto invoice = invoiceService.edit(id, invoiceSaveDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoice);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<InvoiceDto> disabled(@PathVariable("id") Long id) throws DataNotFoundException {
        InvoiceDto invoice = invoiceService.disabled(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoice);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/pagination-filter")
    public ResponseEntity<Page<InvoiceDto>> paginationFilter(Pageable pageable, Optional<InvoiceFilterDto> filter) {
        Page<InvoiceDto> invoiceDtoPage = invoiceService.paginationFilter(pageable, filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(invoiceDtoPage);
    }

}
