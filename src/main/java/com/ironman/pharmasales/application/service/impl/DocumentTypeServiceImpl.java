package com.ironman.pharmasales.application.service.impl;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.ironman.pharmasales.application.dto.documenttype.mapper.DocumentTypeMapper;
import com.ironman.pharmasales.application.service.DocumentTypeService;
import com.ironman.pharmasales.persistence.entity.DocumentType;
import com.ironman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public List<DocumentTypeDto> findAll() {
        List<DocumentType> documentTypes = (List<DocumentType>) documentTypeRepository.findAll();

        return documentTypeMapper.toDocumentTypeDtos(documentTypes);
    }

    @Override
    public DocumentTypeDto findById(Long id) throws DataNotFoundException {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> documentTypeNotFoundException(id));

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto create(DocumentTypeSaveDto documentTypeSaveDto) {
        DocumentType documentTypeSave = documentTypeMapper.toDocumentType(documentTypeSaveDto);
        documentTypeSave.setState(State.ACTIVE.getValue());
        documentTypeSave.setCreatedAt(LocalDateTime.now());

        DocumentType documentType = documentTypeRepository.save(documentTypeSave);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeSaveDto) throws DataNotFoundException {
        DocumentType documentTypeDb = documentTypeRepository.findById(id)
                .orElseThrow(() -> documentTypeNotFoundException(id));

        documentTypeMapper.updateDocumentType(documentTypeDb, documentTypeSaveDto);
        documentTypeDb.setUpdatedAt(LocalDateTime.now());

        DocumentType documentType = documentTypeRepository.save(documentTypeDb);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto disabled(Long id) throws DataNotFoundException {
        DocumentType documentTypeDb = documentTypeRepository.findById(id)
                .orElseThrow(() -> documentTypeNotFoundException(id));

        documentTypeDb.setState(State.DISABLE.getValue());

        DocumentType documentType = documentTypeRepository.save(documentTypeDb);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public List<DocumentTypeSimpleDto> select() {
        List<DocumentType> documentTypes = documentTypeRepository.findByState(State.ACTIVE.getValue());

        return documentTypeMapper.toDocumentTypeSimpleDtos(documentTypes);
    }

    @Override
    public Page<DocumentTypeDto> paginationFilter(Pageable pageable, Optional<DocumentTypeFilterDto> filter) {
        DocumentTypeFilterDto filterDto = filter.orElse(new DocumentTypeFilterDto());

        DocumentType documentType = documentTypeMapper.toDocumentType(filterDto);

        Page<DocumentType> documentTypePage = documentTypeRepository.paginationFilter(pageable, documentType);

        return new PageImpl<>(
                documentTypeMapper.toDocumentTypeDtos(documentTypePage.getContent()),
                documentTypePage.getPageable(),
                documentTypePage.getTotalElements()
        );
    }


    private static DataNotFoundException documentTypeNotFoundException(Long id) {
        return new DataNotFoundException("Tipo de documento no encontrado para el id: " + id);
    }
}
