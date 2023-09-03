package com.iroman.pharmasales.application.dto.documenttype;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DocumentTypeSaveDto {
    @NotEmpty(message = "El campo nombre es requerido.")
    @Size(min = 3, message = "El nombre debe tener como mínimo 3 caracteres.")
    private String name;
    private String description;

    @NotBlank(message = "El campo codigo de sunat es requerido.")
    @Size(min = 2, max = 2, message = "El codigo de sunat es de 2 digitos.")
    private String sunatCode;

    @NotNull(message = "El campo longitud es requerido.")
    @Positive(message = "La longitud debe ser un numero positivo.")
    private Integer size;
    private Boolean isSizeExact;
    private Boolean isNumeric;
}
