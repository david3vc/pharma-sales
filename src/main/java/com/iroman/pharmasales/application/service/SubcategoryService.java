package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;

import java.util.List;
import java.util.Optional;

public interface SubcategoryService {
    List<SubcategoryDto> findAll();
    SubcategoryDto findById(Long id);
    SubcategoryDto create(SubcategorySaveDto subcategoryBody);
    SubcategoryDto edit(Long id, SubcategorySaveDto subcategoryBody);
    SubcategoryDto disable(Long id);
    List<SubcategoryDto> filter(Optional<SubcategoryFilterDto> filter);
}
