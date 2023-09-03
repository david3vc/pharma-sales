package com.iroman.pharmasales.application.dto.client;

import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.iroman.pharmasales.shared.state.enums.State;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientDto {
    private Long id;
    private String name;
    private String lastName;
    private DocumentTypeSimpleDto documentType;
    private String documentNumber;
    private String phoneNumber;
    private String address;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
