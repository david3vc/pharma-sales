package com.iroman.pharmasales.application.dto.documenttype;

import lombok.Data;

@Data
public class DocumentTypeFilterDto {
    private String name;
    private String description;
    private String sunatCode;
    private Integer sizeFilter;
    private Boolean isSizeExact;
    private Boolean isNumeric;
    private String state;
}
