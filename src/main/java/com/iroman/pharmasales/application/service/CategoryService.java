package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.CategorySimpleDto;
import com.iroman.pharmasales.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
    CategoryDto create(CategorySaveDto categoryBody);
    CategoryDto edit(Long id, CategorySaveDto categoryBody);
    CategoryDto disable(Long id);
    List<CategorySimpleDto> select();
    Page<CategoryDto> pagination(Pageable pageable);
}
