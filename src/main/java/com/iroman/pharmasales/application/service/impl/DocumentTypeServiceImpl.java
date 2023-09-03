package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.iroman.pharmasales.application.dto.documenttype.mapper.DocumentTypeMapper;
import com.iroman.pharmasales.application.service.DocumentTypeService;
import com.iroman.pharmasales.persistence.entity.DocumentType;
import com.iroman.pharmasales.persistence.repository.DocumentTypeRepository;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import com.iroman.pharmasales.shared.state.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public DocumentTypeDto findById(Long id) throws DataNotFoundException {
        DocumentType documentType = documentTypeRepository.findById(id)
                .orElseThrow(() -> documentTypeNotFoundExecption(id));

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
    public DocumentTypeDto edit(Long id, DocumentTypeSaveDto documentTypeBody) throws DataNotFoundException {
        DocumentType documentTypeDb = documentTypeRepository.findById(id).orElseThrow(() -> documentTypeNotFoundExecption(id));

        DocumentType documentTypeSave = documentTypeMapper.toDocumentType(documentTypeBody);

        documentTypeSave.setId(documentTypeDb.getId());
        documentTypeSave.setState(documentTypeDb.getState());
        documentTypeSave.setCreatedAt(documentTypeDb.getCreatedAt());
        documentTypeSave.setUpdatedAt(LocalDateTime.now());

        DocumentType documentType = documentTypeRepository.save(documentTypeSave);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public DocumentTypeDto disable(Long id) throws DataNotFoundException {
        DocumentType documentTypeDb = documentTypeRepository.findById(id).orElseThrow(() -> documentTypeNotFoundExecption(id));
        documentTypeDb.setState(State.DISABLE.getValue());

        DocumentType documentType = documentTypeRepository.save(documentTypeDb);

        return documentTypeMapper.toDocumentTypeDto(documentType);
    }

    @Override
    public List<DocumentTypeSimpleDto> select() {
        List<DocumentType> documentTypes = documentTypeRepository.findByState(State.ACTIVE.getValue());

        return documentTypeMapper.toDocumentTypeSimpleDtos(documentTypes);
    }

    @Override
    public List<DocumentTypeSimpleDto> searchByState(String state) {
        List<DocumentType> documentTypes = documentTypeRepository.searchByState(state);

        return documentTypeMapper.toDocumentTypeSimpleDtos(documentTypes);
    }

    @Override
    public Page<DocumentTypeDto> paginationFilter(Pageable pageable, Optional<DocumentTypeFilterDto> filter) {
        DocumentTypeFilterDto filterDto = filter.orElse((new DocumentTypeFilterDto()));

        DocumentType documentType = documentTypeMapper.toDocumentType(filterDto);

        Page<DocumentType> documentTypePage = documentTypeRepository.paginationFilter(pageable, documentType);

        List<DocumentTypeDto> documentTypeDtos = documentTypeMapper.toDocumentTypeDtos(documentTypePage.getContent());

        Page<DocumentTypeDto> documentTypeDtoPage = new PageImpl<>(documentTypeDtos, documentTypePage.getPageable(), documentTypePage.getTotalElements());

        return documentTypeDtoPage;
    }

    private static DataNotFoundException documentTypeNotFoundExecption(Long id){
        return new DataNotFoundException(("DocumentType no encontrado para el id: " + id));
    }
}
