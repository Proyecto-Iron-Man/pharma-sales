package com.ironman.pharmasales.application.dto.client;

import lombok.Data;

@Data
public class ClientFilterDto {
    private String name;
    private String lastName;
    private Long documentTypeId;
    private String documentNumber;
    private String state;
}
