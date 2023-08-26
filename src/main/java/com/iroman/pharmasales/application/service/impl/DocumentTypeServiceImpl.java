package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.dto.documentType.mapper.DocumentTypeMapper;
import com.iroman.pharmasales.application.service.DocumentTypeService;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.entity.DocumentType;
import com.iroman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.iroman.pharmasales.shared.state.enums.State;
import com.iroman.pharmasales.shared.string.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentTypeServiceImpl implements DocumentTypeService {
    private final DocumentTypeRepository documentTypeRepository;
    private final DocumentTypeMapper documentTypeMapper;

    @Override
    public List<DocumentTypeDto> findAll() {
        List<DocumentType> documentTypes = (List<DocumentType>) documentTypeRepository.findAll();

        return documentTypeMapper.toDocumentTypeDtos(documentTypes);
    }

    @Override
    public DocumentTypeDto findById(Long id) {
        DocumentType documentType = documentTypeRepository.findById(id).get();

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto create(DocumentTypeSaveDto documentTypeBody) {
        DocumentType documentTypeSave = documentTypeMapper.toDocumentType(documentTypeBody);
        documentTypeSave.setState(State.ACTIVE.getValue());
        documentTypeSave.setCreatedAt(LocalDateTime.now());

        DocumentType documentType = documentTypeRepository.save(documentTypeSave);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeBody) {
        DocumentType documentTypeDb = documentTypeRepository.findById(id).get();

        DocumentType documentTypeSave = documentTypeMapper.toDocumentType(documentTypeBody);

        documentTypeSave.setId(documentTypeDb.getId());
        documentTypeSave.setState(documentTypeDb.getState());
        documentTypeSave.setCreatedAt(documentTypeDb.getCreatedAt());
        documentTypeSave.setUpdatedAt(LocalDateTime.now());

        DocumentType documentType = documentTypeRepository.save(documentTypeSave);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto disable(Long id) {
        DocumentType documentTypeDb = documentTypeRepository.findById(id).get();
        documentTypeDb.setState(State.DISABLE.getValue());

        DocumentType documentType = documentTypeRepository.save(documentTypeDb);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public Page<DocumentTypeDto> pagination(Pageable pageable) {
        Page<DocumentType> documentTypePage = documentTypeRepository.findAll(pageable);

        List<DocumentTypeDto> documentTypeDtos = documentTypeMapper.toDocumentTypeDtos(documentTypePage.getContent());

        Page<DocumentTypeDto> documentTypeDtoPage = new PageImpl<>(documentTypeDtos, documentTypePage.getPageable(), documentTypePage.getTotalElements());

        return documentTypeDtoPage;
    }
}
