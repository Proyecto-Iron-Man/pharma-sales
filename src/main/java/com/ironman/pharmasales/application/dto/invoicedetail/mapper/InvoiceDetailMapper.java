package com.ironman.pharmasales.application.dto.invoicedetail.mapper;

import com.ironman.pharmasales.application.dto.invoicedetail.InvoiceDetailDto;
import com.ironman.pharmasales.application.dto.invoicedetail.InvoiceDetailSaveDto;
import com.ironman.pharmasales.application.dto.product.mapper.ProductMapper;
import com.ironman.pharmasales.persistence.entity.InvoiceDetail;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, ProductMapper.class}
)
public interface InvoiceDetailMapper {
    // Dto from Entity Start
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    InvoiceDetailDto toInvoiceDetailDto(InvoiceDetail invoiceDetail);

    List<InvoiceDetailDto> toInvoiceDetailDtos(List<InvoiceDetail> invoiceDetails);
    // Dto from Entity End

    // Entity from Dto Start

    @Mapping(target = "id.productId", source = "productId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "id.invoiceId", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    InvoiceDetail toInvoiceDetail(InvoiceDetailSaveDto invoiceDetailSaveDto);

    @InheritConfiguration
    void updateInvoiceDetail(@MappingTarget InvoiceDetail invoiceDetail, InvoiceDetailSaveDto invoiceDetailSaveDto);

    List<InvoiceDetail> toInvoiceDetails(List<InvoiceDetailSaveDto> invoiceDetailSaveDtos);
    // Entity from Dto End

}
