package com.ironman.pharmasales.application.dto.documenttype.mapper;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.ironman.pharmasales.persistence.entity.DocumentType;
import com.ironman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {StateMapper.class}
)
public interface DocumentTypeMapper {
    int IS_TRUE = 1;
    boolean IS_ACTIVE = true;


    // Dto from Entity Start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", expression = "java(documentType.getIsSizeExact() == IS_TRUE)")
    @Mapping(target = "isNumeric", expression = "java(documentType.getIsNumeric() == IS_TRUE)")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DocumentTypeDto toDocumentTypeDto(DocumentType documentType);

    List<DocumentTypeDto> toDocumentTypeDtos(List<DocumentType> documentTypes);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DocumentTypeSimpleDto toDocumentTypeSimpleDto(DocumentType documentType);

    List<DocumentTypeSimpleDto> toDocumentTypeSimpleDtos(List<DocumentType> documentTypes);
    // Dto from Entity End


    // Entity from Dto Start

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", expression = "java(saveDto.getIsSizeExact() == IS_ACTIVE ? 1: 0)")
    @Mapping(target = "isNumeric", expression = "java(saveDto.getIsNumeric() == IS_ACTIVE ? 1: 0)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DocumentType toDocumentType(DocumentTypeSaveDto saveDto);

    @InheritConfiguration
    void updateDocumentType(@MappingTarget DocumentType documentType, DocumentTypeSaveDto saveDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "sizeDocument")
    @Mapping(target = "isSizeExact", source = ".", qualifiedByName = "getIsSizeExact")
    @Mapping(target = "isNumeric", source = ".", qualifiedByName = "getIsNumeric")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DocumentType toDocumentType(DocumentTypeFilterDto filterDto);

    @Named("getIsSizeExact")
    default Integer getIsSizeExact(DocumentTypeFilterDto filterDto) {
        Integer isSizeExact = null;

        Optional<DocumentTypeFilterDto> filter = Optional.ofNullable(filterDto);

        if (filter.isPresent() && filter.get().getIsSizeExact() != null)
            isSizeExact = filter.get().getIsSizeExact() == IS_ACTIVE ? 1 : 0;

        return isSizeExact;
    }

    @Named("getIsNumeric")
    default Integer getIsNumeric(DocumentTypeFilterDto filterDto) {
        Integer isNumeric = null;

        Optional<DocumentTypeFilterDto> filter = Optional.ofNullable(filterDto);

        if (filter.isPresent() && filter.get().getIsNumeric() != null)
            isNumeric = filter.get().getIsNumeric() == IS_ACTIVE ? 1 : 0;

        return isNumeric;
    }
    // Entity from Dto End

}
