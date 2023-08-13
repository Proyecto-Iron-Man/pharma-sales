package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    List<DocumentTypeDto> findAll();

    DocumentTypeDto findById(Long id) throws DataNotFoundException;

    DocumentTypeDto create(DocumentTypeSaveDto documentTypeSaveDto);

    DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeSaveDto) throws DataNotFoundException;

    DocumentTypeDto disabled(Long id) throws DataNotFoundException;

    List<DocumentTypeSimpleDto> select();

    Page<DocumentTypeDto> paginationFilter(Pageable pageable, Optional<DocumentTypeFilterDto> filter);
}
