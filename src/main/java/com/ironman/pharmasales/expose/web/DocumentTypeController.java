package com.ironman.pharmasales.expose.web;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.ironman.pharmasales.application.service.DocumentTypeService;
import com.ironman.pharmasales.shared.constant.StatusCode;
import com.ironman.pharmasales.shared.exception.DataNotFoundException;
import com.ironman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.ironman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("document-type")
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;


    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<DocumentTypeDto>> findAll() {
        List<DocumentTypeDto> documentTypes = documentTypeService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentTypes);
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
    public ResponseEntity<DocumentTypeDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentType);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    public ResponseEntity<DocumentTypeDto> create(@Valid @RequestBody DocumentTypeSaveDto documentTypeSaveDto) {
        DocumentTypeDto documentType = documentTypeService.create(documentTypeSaveDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(documentType);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<DocumentTypeDto> edit(@PathVariable("id") Long id, @Valid @RequestBody DocumentTypeSaveDto documentTypeSaveDto)
            throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.edit(id, documentTypeSaveDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentType);
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
    public ResponseEntity<DocumentTypeDto> disabled(@PathVariable("id") Long id) throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.disabled(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentType);
    }


}
