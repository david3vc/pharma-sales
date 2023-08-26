package com.iroman.pharmasales.application.dto.documentType;

import com.iroman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DocumentTypeDto {
    private Long id;
    private String name;
    private String sunatCode;
    private Integer size;
    private Integer isSizeExact;
    private Integer isNumeric;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
