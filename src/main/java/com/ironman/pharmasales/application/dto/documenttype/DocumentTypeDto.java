package com.ironman.pharmasales.application.dto.documenttype;

import com.ironman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentTypeDto {
    private Long id;
    private String name;
    private String description;
    private String sunatCode;
    private Integer size;
    private Boolean isSizeExact;
    private Boolean isNumeric;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
