package com.ironman.pharmasales.application.dto.client;

import com.ironman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import lombok.Data;

@Data
public class ClientMediumDto {
    private Long id;
    private String fullName;
    private DocumentTypeSimpleDto documentType;
    private String documentNumber;
    private String phoneNumber;
    private String address;
}
