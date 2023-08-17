package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.iroman.pharmasales.application.service.SubcategoryService;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.persistence.repository.CategoryRepository;
import com.iroman.pharmasales.persistence.repository.SubcategoryRepository;
import com.iroman.pharmasales.shared.exception.DataNotFoundException;
import com.iroman.pharmasales.shared.state.enums.State;
import com.iroman.pharmasales.shared.string.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public List<SubcategoryDto> findAll() {
        List<Subcategory> subcategories = (List<Subcategory>) subcategoryRepository.findAll();

        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public SubcategoryDto findById(Long id) throws DataNotFoundException {
        Subcategory subcategory = subcategoryRepository.findById(id)
                .orElseThrow(() -> subcategoryNotFoundException(id));

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto create(SubcategorySaveDto subcategoryBody) throws DataNotFoundException {
        Category category = categoryRepository.findById(subcategoryBody.getCategoryId())
                .orElseThrow(()-> categoryNotFoundException(subcategoryBody));

        Subcategory subcategorySave = subcategoryMapper.toSubcategory(subcategoryBody);

        subcategorySave.setKeyword(new StringHelper().slugsKeywords(subcategorySave.getName()));
        subcategorySave.setCategory(category);
        subcategorySave.setState(State.ACTIVE.getValue());
        subcategorySave.setCreatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategorySave);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto edit(Long id, SubcategorySaveDto subcategoryBody) throws DataNotFoundException {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).orElseThrow(() -> subcategoryNotFoundException(id));

        Category category = categoryRepository.findById(subcategoryBody.getCategoryId())
                .orElseThrow(()-> categoryNotFoundException(subcategoryBody));

        subcategoryMapper.updateSubcategory(subcategoryDb, subcategoryBody);

        subcategoryDb.setKeyword(new StringHelper().slugsKeywords(subcategoryBody.getName()));
        subcategoryDb.setCategory(category);
        subcategoryDb.setUpdatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategoryDb);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto disable(Long id) throws DataNotFoundException {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).orElseThrow(() -> subcategoryNotFoundException(id));
        subcategoryDb.setState(State.DISABLE.getValue());

        Subcategory subcategory = subcategoryRepository.save(subcategoryDb);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public List<SubcategoryDto> filter(Optional<SubcategoryFilterDto> filter) {

        SubcategoryFilterDto filterDto = filter.orElse(new SubcategoryFilterDto());

        Subcategory subcategory = subcategoryMapper.toSubcategory(filterDto);

        List<Subcategory> subcategories = subcategoryRepository.filter(subcategory);

        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public Page<SubcategoryDto> paginationFilter(Pageable pageable, Optional<SubcategoryFilterDto> filter) {
        SubcategoryFilterDto filterDto = filter.orElse(new SubcategoryFilterDto());

        Subcategory subcategory = subcategoryMapper.toSubcategory(filterDto);

        Page<Subcategory> subcategoryPage = subcategoryRepository.paginationFilter(pageable, subcategory);

        List<SubcategoryDto> subcategoryDtos = subcategoryMapper.toSubcategoryDtos(subcategoryPage.getContent());

        Page<SubcategoryDto> subcategoryDtoPage = new PageImpl<>(subcategoryDtos, subcategoryPage.getPageable(), subcategoryPage.getTotalElements());

        return subcategoryDtoPage;
    }

    private static DataNotFoundException subcategoryNotFoundException(Long id) {
        return new DataNotFoundException("Subcategoría no encontrada para el id: " + id);
    }

    private static DataNotFoundException categoryNotFoundException(SubcategorySaveDto subcategoryBody) {
        return new DataNotFoundException("Categoría no encontrada para el id: " + subcategoryBody.getCategoryId());
    }
}
