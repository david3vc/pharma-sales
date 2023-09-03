package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DocumentTypeService {
    List<DocumentTypeDto> findAll();
    DocumentTypeDto findById(Long id) throws DataNotFoundException;
    DocumentTypeDto create(DocumentTypeSaveDto documentTypeBody);
    DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeBody) throws DataNotFoundException;
    DocumentTypeDto disable(Long id) throws DataNotFoundException;
    List<DocumentTypeSimpleDto> select();
    List<DocumentTypeSimpleDto> searchByState(String state);
    Page<DocumentTypeDto> paginationFilter(Pageable pageable, Optional<DocumentTypeFilterDto> filter);
}
