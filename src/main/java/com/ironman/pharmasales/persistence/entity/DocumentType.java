package com.ironman.pharmasales.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "document_types")
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "sunat_code")
    private String sunatCode;

    private Integer size;

    @Column(name = "is_size_exact")
    private  Integer isSizeExact;

    @Column(name = "is_numeric")
    private Integer isNumeric;

    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
