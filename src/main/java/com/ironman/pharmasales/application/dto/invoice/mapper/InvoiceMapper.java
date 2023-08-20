package com.ironman.pharmasales.application.dto.invoice.mapper;

import com.ironman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.dto.invoicedetail.mapper.InvoiceDetailMapper;
import com.ironman.pharmasales.persistence.entity.Invoice;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, ClientMapper.class, InvoiceDetailMapper.class}
)
public interface InvoiceMapper {
    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "invoiceDate", source = "invoiceDate")
    @Mapping(target = "client", source = "client")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "invoiceDetails", source = "invoiceDetails")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    InvoiceDto toInvoiceDto(Invoice invoice);

    List<InvoiceDto> toInvoiceDtos(List<Invoice> invoices);
    // Dto from Entity End

    // Entity from Dto Start
    @Mapping(target = "invoiceDate", source = "invoiceDate")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "userId", source = "userId")
    @Mapping(target = "invoiceDetails", source = "invoiceDetails")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Invoice toInvoice(InvoiceSaveDto invoiceSaveDto);

    @InheritConfiguration
    void updateInvoice(@MappingTarget Invoice invoice, InvoiceSaveDto invoiceSaveDto);
    // Entity from Dto End

}
