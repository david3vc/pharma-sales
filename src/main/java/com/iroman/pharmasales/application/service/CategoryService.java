package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category create(CategorySaveDto categoryBody);
    Category edit(Long id, CategorySaveDto categoryBody);
    Category disable(Long id);
}
