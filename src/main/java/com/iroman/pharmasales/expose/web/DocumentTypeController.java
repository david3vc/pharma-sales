package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeFilterDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSaveDto;
import com.iroman.pharmasales.application.dto.documenttype.DocumentTypeSimpleDto;
import com.iroman.pharmasales.application.service.DocumentTypeService;
import com.iroman.pharmasales.shared.constant.StatusCode;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import com.iroman.pharmasales.shared.exception.entity.ArgumentNotValidError;
import com.iroman.pharmasales.shared.exception.entity.GeneralError;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/document-type")
@RequiredArgsConstructor
@RestController
public class DocumentTypeController {
    private final DocumentTypeService documentTypeService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<DocumentTypeDto>> findAll() {
        List<DocumentTypeDto> documentTypes = documentTypeService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentTypes);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @GetMapping("/{id}")
    ResponseEntity<DocumentTypeDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(documentType);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @PostMapping
    ResponseEntity<DocumentTypeDto> create(@Valid @RequestBody DocumentTypeSaveDto documentTypeBody) {
        DocumentTypeDto documentType = documentTypeService.create(documentTypeBody);

        return ResponseEntity.ok(documentType);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.BAD_REQUEST,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ArgumentNotValidError.class)
            )
    )
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @PutMapping("/{id}")
    ResponseEntity<DocumentTypeDto> edit(@PathVariable("id") Long id, @Valid @RequestBody DocumentTypeSaveDto documentTypeBody) throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.edit(id, documentTypeBody);

        return ResponseEntity.ok(documentType);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @ApiResponse(
            responseCode = StatusCode.NOT_FOUND,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = GeneralError.class)
            )
    )
    @DeleteMapping("/{id}")
    ResponseEntity<DocumentTypeDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        DocumentTypeDto documentType = documentTypeService.disable(id);

        return ResponseEntity.ok(documentType);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/select")
    ResponseEntity<List<DocumentTypeSimpleDto>> select() {
        List<DocumentTypeSimpleDto> documentTypes = documentTypeService.select();

        return ResponseEntity.ok(documentTypes);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/search-by-state/{state}")
    ResponseEntity<List<DocumentTypeSimpleDto>> searchByState(@PathVariable("state") String state) {
        List<DocumentTypeSimpleDto> documentTypes = documentTypeService.searchByState(state);

        return ResponseEntity.ok(documentTypes);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/pagination-filter")
    ResponseEntity<Page<DocumentTypeDto>> pagination(Pageable pageable, Optional<DocumentTypeFilterDto> filter) {
        Page<DocumentTypeDto> documentTypeDtoPage = documentTypeService.paginationFilter(pageable, filter);

        return ResponseEntity.ok(documentTypeDtoPage);
    }
}
