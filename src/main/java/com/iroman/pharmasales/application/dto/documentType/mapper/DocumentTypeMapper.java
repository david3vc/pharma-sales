package com.iroman.pharmasales.application.dto.documentType.mapper;

import com.iroman.pharmasales.application.dto.documentType.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeSaveDto;
import com.iroman.pharmasales.persistence.entity.DocumentType;
import com.iroman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class})
public interface DocumentTypeMapper {
    // Dto from Entity start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", source = "isSizeExact")
    @Mapping(target = "isNumeric", source = "isNumeric")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DocumentTypeDto toDocumentTypeDto(DocumentType documentType);
    List<DocumentTypeDto> toDocumentTypeDtos(List<DocumentType> documentType);
    // Entity to Dto end

    // Entity from Dto start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", source = "isSizeExact")
    @Mapping(target = "isNumeric", source = "isNumeric")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DocumentType toDocumentType(DocumentTypeSaveDto documentTypeSaveDto);
    // Entity from Dto end
}
