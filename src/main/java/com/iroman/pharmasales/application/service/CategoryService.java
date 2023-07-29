package com.iroman.pharmasales.application.service;

import com.iroman.pharmasales.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category create(Category categoryBody);
    Category edit(Long id, Category categoryBody);
    Category disable(Long id);
}
