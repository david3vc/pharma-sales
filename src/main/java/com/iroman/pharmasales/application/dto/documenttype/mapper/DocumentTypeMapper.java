package com.iroman.pharmasales.application.dto.documenttype.mapper;

import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.iroman.pharmasales.persistence.entity.DocumentType;
import com.iroman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class})
public interface DocumentTypeMapper {
    int IS_TRUE = 1;
    boolean IS_ACTIVE = true;

    // Dto from Entity start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", expression = "java(documentType.getIsSizeExact() == IS_TRUE)")
    @Mapping(target = "isNumeric", expression = "java(documentType.getIsNumeric() == IS_TRUE)")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    DocumentTypeDto toDocumentTypeDto(DocumentType documentType);
    List<DocumentTypeDto> toDocumentTypeDtos(List<DocumentType> documentTypes);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DocumentTypeSimpleDto toDocumentTypeSimpleDto(DocumentType documentType);
    List<DocumentTypeSimpleDto> toDocumentTypeSimpleDtos(List<DocumentType> documentTypes);
    // Entity to Dto end

    // Entity from Dto start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "size")
    @Mapping(target = "isSizeExact", expression = "java(documentTypeSaveDto.getIsSizeExact() == IS_ACTIVE ? 1 : 0)")
    @Mapping(target = "isNumeric", expression = "java(documentTypeSaveDto.getIsNumeric() == IS_ACTIVE ? 1 : 0)")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DocumentType toDocumentType(DocumentTypeSaveDto documentTypeSaveDto);
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "sunatCode", source = "sunatCode")
    @Mapping(target = "size", source = "sizeFilter")
    @Mapping(target = "isSizeExact", source = ".", qualifiedByName = "getIsSizeExact")
    @Mapping(target = "isNumeric", source = ".", qualifiedByName = "getIsNumeric")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    DocumentType toDocumentType(DocumentTypeFilterDto documentTypeFilterDto);

    @Named("getIsSizeExact")
    default Integer getIsSizeExact(DocumentTypeFilterDto documentTypeFilterDto) {
        Integer isSizeExact = null;

        Optional<DocumentTypeFilterDto> filter = Optional.ofNullable(documentTypeFilterDto);

        if(filter.isPresent() && filter.get().getIsSizeExact() != null)
            isSizeExact = filter.get().getIsSizeExact() == IS_ACTIVE ? 1 : 0;

        return isSizeExact;
    }
    @Named("getIsNumeric")
    default Integer getIsNumeric(DocumentTypeFilterDto documentTypeFilterDto) {
        Integer isNumeric = null;

        Optional<DocumentTypeFilterDto> filter = Optional.ofNullable(documentTypeFilterDto);

        if(filter.isPresent() && filter.get().getIsNumeric() != null)
            isNumeric = filter.get().getIsNumeric() == IS_ACTIVE ? 1 : 0;

        return isNumeric;
    }
    // Entity from Dto end
}
