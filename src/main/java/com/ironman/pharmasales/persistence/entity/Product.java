package com.ironman.pharmasales.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "products",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "subcategory_id"})}
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    private String description;
    private String presentation;

    @Column(name = "unit_measure")
    private String unitMeasure;
    private String prescription;
    private String precaution;

    @Column(name = "side_effect")
    private String sideEffect;

    private BigDecimal price;
    private Long stock;

    @Column(name = "subcategory_id")
    private Long subcategoryId;

    @ManyToOne
    @JoinColumn(name = "subcategory_id", insertable = false, updatable = false)
    private Subcategory subcategory;

    private String state;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}