package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubcategoryService {
    List<SubcategoryDto> findAll();
    SubcategoryDto findById(Long id) throws DataNotFoundException;
    SubcategoryDto create(SubcategorySaveDto subcategoryBody) throws DataNotFoundException;
    SubcategoryDto edit(Long id, SubcategorySaveDto subcategoryBody) throws DataNotFoundException;
    SubcategoryDto disable(Long id) throws DataNotFoundException;
    List<SubcategoryDto> filter(Optional<SubcategoryFilterDto> filter);
    Page<SubcategoryDto> paginationFilter(Pageable pageable, Optional<SubcategoryFilterDto> filter);
}
