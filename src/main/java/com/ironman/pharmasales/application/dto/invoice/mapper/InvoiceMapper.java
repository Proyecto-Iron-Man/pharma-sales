package com.ironman.pharmasales.application.dto.invoice.mapper;

import com.ironman.pharmasales.application.dto.client.mapper.ClientMapper;
import com.ironman.pharmasales.application.dto.invoice.InvoiceDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceFilterDto;
import com.ironman.pharmasales.application.dto.invoice.InvoiceSaveDto;
import com.ironman.pharmasales.application.dto.invoicedetail.mapper.InvoiceDetailMapper;
import com.ironman.pharmasales.persistence.entity.Invoice;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

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

    @Mapping(target = "invoiceDate", source = "invoiceDate", qualifiedByName = "getInvoiceDate")
    @Mapping(target = "clientId", source = "clientId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "invoiceDetails", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Invoice toInvoice(InvoiceFilterDto invoiceFilterDto);

    @Named("getInvoiceDate")
    default LocalDateTime getInvoiceDate(LocalDate invoiceDate) {
        LocalDateTime dateTime = null;

        if (Optional.ofNullable(invoiceDate).isPresent())
            dateTime = invoiceDate.atTime(LocalTime.now());

        return dateTime;
    }
    // Entity from Dto End

}
