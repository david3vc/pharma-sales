package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.service.SubcategoryService;
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

@RequestMapping("/subcategories")
@RequiredArgsConstructor
@RestController
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAll(){
        List<SubcategoryDto> subcategories = subcategoryService.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategories);
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
    public ResponseEntity<SubcategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
    }

    @ApiResponse(responseCode = StatusCode.CREATED)
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
    @PostMapping
    public ResponseEntity<SubcategoryDto> create(@Valid @RequestBody SubcategorySaveDto subcategoryBody) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.create(subcategoryBody);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subcategory);
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
    public ResponseEntity<SubcategoryDto> edit(@Valid @PathVariable("id") Long id, @RequestBody SubcategorySaveDto subcategoryBody) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.edit(id, subcategoryBody);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
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
    public ResponseEntity<SubcategoryDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        SubcategoryDto subcategory = subcategoryService.disable(id);

        return  ResponseEntity.status(HttpStatus.OK)
                .body(subcategory);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/filter")
    public ResponseEntity<List<SubcategoryDto>> filter(Optional<SubcategoryFilterDto> filter){
        List<SubcategoryDto> subcategories = subcategoryService.filter(filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategories);
    }

    @ApiResponse(responseCode = StatusCode.OK)
    @GetMapping("/pagination-filter")
    ResponseEntity<Page<SubcategoryDto>> pagination(Pageable pageable, Optional<SubcategoryFilterDto> filter){
        Page<SubcategoryDto> subcategoryDtoPage = subcategoryService.paginationFilter(pageable, filter);

        return ResponseEntity.status(HttpStatus.OK)
                .body(subcategoryDtoPage);
    }
}
