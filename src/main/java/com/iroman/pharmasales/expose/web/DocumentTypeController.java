package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.documentType.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documentType.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.service.DocumentTypeService;
import com.iroman.pharmasales.shared.constant.StatusCode;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/document-types")
@RequiredArgsConstructor
@RestController
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<DocumentTypeDto>> findAll(){
        List<DocumentTypeDto> documentTypes = documentTypeService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentTypes);
    }

    @GetMapping("/{id}")
    ResponseEntity<DocumentTypeDto> findById(@PathVariable("id") Long id){
        DocumentTypeDto documentType = documentTypeService.findById(id);

        return ResponseEntity.ok(documentType);
    }

    @PostMapping
    ResponseEntity<DocumentTypeDto> create(@RequestBody DocumentTypeSaveDto documentTypeBody){
        DocumentTypeDto documentType = documentTypeService.create(documentTypeBody);

        return ResponseEntity.ok(documentType);
    }

    @PutMapping("/{id}")
    ResponseEntity<DocumentTypeDto> edit(@PathVariable("id") Long id, @RequestBody DocumentTypeSaveDto documentTypeBody){
        DocumentTypeDto documentType = documentTypeService.edit(id, documentTypeBody);

        return ResponseEntity.ok(documentType);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DocumentTypeDto> disable(@PathVariable("id") Long id){
        DocumentTypeDto documentType = documentTypeService.disable(id);

        return  ResponseEntity.ok(documentType);
    }

    @GetMapping("/pagination")
    ResponseEntity<Page<DocumentTypeDto>> pagination(Pageable pageable){
        Page<DocumentTypeDto> documentTypeDtoPage = documentTypeService.pagination(pageable);

        return ResponseEntity.ok(documentTypeDtoPage);
    }
}
