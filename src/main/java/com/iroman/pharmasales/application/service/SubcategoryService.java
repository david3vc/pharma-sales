package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;

import java.util.List;

public interface SubcategoryService {
    List<SubcategoryDto> findAll();
    SubcategoryDto findById(Long id);
    SubcategoryDto create(SubcategorySaveDto subcategorySaveDto);
    SubcategoryDto edit(Long id, SubcategorySaveDto subcategorySaveDto);
    SubcategoryDto disable(Long id);
}
