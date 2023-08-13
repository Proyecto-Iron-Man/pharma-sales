package com.ironman.pharmasales.application.service;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeDto> findAll();

    DocumentTypeDto findById(Long id) throws DataNotFoundException;

    DocumentTypeDto create(DocumentTypeSaveDto documentTypeSaveDto);

    DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeSaveDto) throws DataNotFoundException;

    DocumentTypeDto disabled(Long id) throws DataNotFoundException;
}
