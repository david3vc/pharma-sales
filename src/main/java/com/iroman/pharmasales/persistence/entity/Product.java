package com.iroman.pharmasales.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String presentation;
    @Column(name = "unit_measure")
    private String unitMeasure;
    private String prescription;
    private String precaution;
    @Column(name = "side_effect")
    private String sideEffect;
    private String price;
    private String stock;
    private String state;
    @Column(name = "subcategory_id")
    private Long subcategoryId;
    @ManyToOne
    @JoinColumn(name = "subcategory_id", insertable = false, updatable = false)
    private Subcategory subcategory;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
