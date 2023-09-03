package com.iroman.pharmasales.application.dto.client;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientSaveDto {
    @NotEmpty(message = "El campo nombre es requerido")
    private String name;
    private String lastName;

    @NotNull(message = "El campo tipo de documento es requerido")
    @Positive(message = "El campo tipo de documento debe ser un numero positivo")
    private Long documentTypeId;

    @NotEmpty(message = "El campo numero de documento es requerido")
    @Size(max = 20, message = "El campo numero de documento no debe exceder los 20 caracteres")
    private String documentNumber;
    private String phoneNumber;
    private String address;
}
