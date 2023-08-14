package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.CategorySimpleDto;
import com.iroman.pharmasales.application.dto.category.mapper.CategoryMapper;
import com.iroman.pharmasales.application.service.CategoryService;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.repository.CategoryRepository;
import com.iroman.pharmasales.shared.state.enums.State;
import com.iroman.pharmasales.shared.string.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public CategoryDto create(CategorySaveDto categoryBody) {
        Category categorySave = categoryMapper.toCategory(categoryBody);
        categorySave.setKeyword(new StringHelper().slugsKeywords(categorySave.getName()));
        categorySave.setState(State.ACTIVE.getValue());
        categorySave.setCreatedAt(LocalDateTime.now());

        Category category = categoryRepository.save(categorySave);

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto edit(Long id, CategorySaveDto categoryBody) {
        Category categoryDb = categoryRepository.findById(id).get();

        Category categorySave = categoryMapper.toCategory(categoryBody);
        categorySave.setKeyword(new StringHelper().slugsKeywords(categorySave.getName()));

        categorySave.setId(categoryDb.getId());
        categorySave.setState(categoryDb.getState());
        categorySave.setCreatedAt(categoryDb.getCreatedAt());
        categorySave.setUpdatedAt(LocalDateTime.now());

        Category category = categoryRepository.save(categorySave);

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDto disable(Long id) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setState(State.DISABLE.getValue());

        Category category = categoryRepository.save(categoryDb);

        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public List<CategorySimpleDto> select() {
        List<Category> categories = categoryRepository.findByState(State.ACTIVE.getValue());

        return categoryMapper.toCategorySimpleDtos(categories);
    }

    @Override
    public List<CategorySimpleDto> searchByState(String state) {
        List<Category> categories = categoryRepository.searchByState(state);

        return categoryMapper.toCategorySimpleDtos(categories);
    }

    @Override
    public Page<CategoryDto> pagination(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryDto> categoryDtos = categoryMapper.toCategoryDtos(categoryPage.getContent());

        Page<CategoryDto> categoryDtoPage = new PageImpl<>(categoryDtos, categoryPage.getPageable(), categoryPage.getTotalElements());

        return categoryDtoPage;
    }
}
