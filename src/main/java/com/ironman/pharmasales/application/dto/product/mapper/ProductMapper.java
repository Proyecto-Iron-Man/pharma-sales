package com.ironman.pharmasales.application.dto.product.mapper;

import com.ironman.pharmasales.application.dto.product.ProductDto;
import com.ironman.pharmasales.application.dto.product.ProductFilterDto;
import com.ironman.pharmasales.application.dto.product.ProductMediumDto;
import com.ironman.pharmasales.application.dto.product.ProductSaveDto;
import com.ironman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.ironman.pharmasales.persistence.entity.Product;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class, SubcategoryMapper.class}
)
public interface ProductMapper {
    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "presentation", source = "presentation")
    @Mapping(target = "unitMeasure", source = "unitMeasure")
    @Mapping(target = "prescription", source = "prescription")
    @Mapping(target = "precaution", source = "precaution")
    @Mapping(target = "sideEffect", source = "sideEffect")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "subcategory", source = "subcategory")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDtos(List<Product> products);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "presentation", source = "presentation")
    @Mapping(target = "unitMeasure", source = "unitMeasure")
    @Mapping(target = "prescription", source = "prescription")
    @Mapping(target = "precaution", source = "precaution")
    @Mapping(target = "sideEffect", source = "sideEffect")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "subcategory", source = "subcategory")
    ProductMediumDto toProductMediumDto(Product product);

    List<ProductMediumDto> toProductMediumDtos(List<Product> products);
    // Dto from Entity End

    // Entity from Dto Start

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "presentation", source = "presentation")
    @Mapping(target = "unitMeasure", source = "unitMeasure")
    @Mapping(target = "prescription", source = "prescription")
    @Mapping(target = "precaution", source = "precaution")
    @Mapping(target = "sideEffect", source = "sideEffect")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "subcategoryId", source = "subcategoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProduct(ProductSaveDto productSaveDto);

    @InheritConfiguration
    void updateProduct(@MappingTarget Product product, ProductSaveDto productSaveDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "presentation", source = "presentation")
    @Mapping(target = "subcategoryId", source = "subcategoryId")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "unitMeasure", ignore = true)
    @Mapping(target = "prescription", ignore = true)
    @Mapping(target = "precaution", ignore = true)
    @Mapping(target = "sideEffect", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "stock", ignore = true)
    @Mapping(target = "subcategory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toProduct(ProductFilterDto productFilterDto);
    // Entity from Dto End
}
