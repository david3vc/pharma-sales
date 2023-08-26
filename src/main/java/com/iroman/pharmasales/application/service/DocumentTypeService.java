package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.CategorySimpleDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeSaveDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentTypeDto> findAll();
    DocumentTypeDto findById(Long id);
    DocumentTypeDto create(DocumentTypeSaveDto documentTypeBody);
    DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeBody);
    DocumentTypeDto disable(Long id);
    Page<DocumentTypeDto> pagination(Pageable pageable);
}
