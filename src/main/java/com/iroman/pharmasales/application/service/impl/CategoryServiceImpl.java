package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.mapper.CategoryMapper;
import com.iroman.pharmasales.application.service.CategoryService;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /* public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }*/
    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categoryMapper.toCategoryDtos(categories);

        return categoryDtos;
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = categoryMapper.toCategoryDto(category);

        return categoryDto;
    }

    @Override
    public Category create(CategorySaveDto categoryBody) {
        Category categorySave = categoryMapper.toCategory(categoryBody);
        categorySave.setKeyword(categoryBody.getName());

        categorySave.setState("A");
        categorySave.setCreatedAt(LocalDateTime.now());

        Category category = categoryRepository.save(categorySave);

        return category;
    }

    @Override
    public Category edit(Long id, CategorySaveDto categoryBody) {
        Category categoryDb = categoryRepository.findById(id).get();

        Category categorySave = categoryMapper.toCategory(categoryBody);

        categorySave.setId(categoryDb.getId());
        categorySave.setKeyword(categorySave.getName());
        categorySave.setState(categoryDb.getState());
        categorySave.setCreatedAt(categoryDb.getCreatedAt());
        categorySave.setUpdatedAt(LocalDateTime.now());

        Category category = categoryRepository.save(categorySave);

        return category;
    }

    @Override
    public Category disable(Long id) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setState("E");

        Category category = categoryRepository.save(categoryDb);

        return category;
    }
}
