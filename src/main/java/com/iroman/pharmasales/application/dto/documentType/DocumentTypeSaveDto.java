package com.iroman.pharmasales.application.dto.documentType;

import lombok.Data;

@Data
public class DocumentTypeSaveDto {
    private String name;
    private String sunatCode;
    private Integer size;
    private Integer isSizeExact;
    private Integer isNumeric;
}
