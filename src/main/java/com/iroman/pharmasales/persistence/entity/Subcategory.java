package com.iroman.pharmasales.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="subcategories")
@Entity
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    private String description;
    private String keyword;
    private String state;
    @Column(name = "category_id")
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
