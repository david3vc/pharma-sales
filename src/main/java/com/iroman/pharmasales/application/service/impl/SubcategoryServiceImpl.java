package com.iroman.pharmasales.application.service.impl;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.dto.subcategory.mapper.SubcategoryMapper;
import com.iroman.pharmasales.application.service.SubcategoryService;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.persistence.repository.SubcategoryRepository;
import com.iroman.pharmasales.shared.state.enums.State;
import com.iroman.pharmasales.shared.string.StringHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public SubcategoryDto create(SubcategorySaveDto subcategoryBody) {
        Subcategory subcategorySave = subcategoryMapper.toSubcategory(subcategoryBody);
        subcategorySave.setKeyword(new StringHelper().slugsKeywords(subcategorySave.getName()));
        subcategorySave.setState(State.ACTIVE.getValue());
        subcategorySave.setCreatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategorySave);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto edit(Long id, SubcategorySaveDto subcategoryBody) {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).get();

        subcategoryMapper.updateSubcategory(subcategoryDb, subcategoryBody);

        subcategoryDb.setKeyword(new StringHelper().slugsKeywords(subcategoryBody.getName()));
        subcategoryDb.setUpdatedAt(LocalDateTime.now());

        Subcategory subcategory = subcategoryRepository.save(subcategoryDb);

        return subcategoryMapper.toSubcategoryDto(subcategory);
    }

    @Override
    public SubcategoryDto disable(Long id) {
        Subcategory subcategoryDb = subcategoryRepository.findById(id).get();
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
}
