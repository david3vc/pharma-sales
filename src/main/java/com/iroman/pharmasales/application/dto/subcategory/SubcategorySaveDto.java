package com.iroman.pharmasales.application.dto.subcategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SubcategorySaveDto {
    @NotBlank(message = "El campo nombre es requerido")
    private String name;
    private String description;

    @NotNull(message = "El campo categoria es requerido")
    @Positive(message = "El campo categoria debe ser un numero positivo")
    private Long categoryId;
}
