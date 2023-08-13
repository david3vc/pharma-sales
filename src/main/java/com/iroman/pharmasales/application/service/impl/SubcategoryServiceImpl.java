package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.iroman.pharmasales.application.service.SubcategoryService;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.persistence.repository.SubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final SubcategoryMapper subcategoryMapper;
    @Override
    public List<SubcategoryDto> findAll() {
        List<Subcategory> subcategories = (List<Subcategory>) subcategoryRepository.findAll();

        return subcategoryMapper.toSubcategoryDtos(subcategories);
    }

    @Override
    public SubcategoryDto findById(Long id) {
        Subcategory subcategory = subcategoryRepository.findById(id).get();

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto create(SubcategorySaveDto subcategorySaveDto) {
        return null;
    }

    @Override
    public SubcategoryDto edit(Long id, SubcategorySaveDto subcategorySaveDto) {
        return null;
    }

    @Override
    public SubcategoryDto disable(Long id) {
        return null;
    }
}
