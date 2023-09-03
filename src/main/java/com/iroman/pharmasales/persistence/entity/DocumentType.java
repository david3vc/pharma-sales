package com.iroman.pharmasales.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="document_types")
@Entity
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    @Column(name = "sunat_code")
    private String sunatCode;
    private Integer size;
    @Column(name = "is_size_exact")
    private Integer isSizeExact;
    @Column(name = "is_numeric")
    private Integer isNumeric;
    private String state;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
