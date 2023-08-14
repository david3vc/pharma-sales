package com.iroman.pharmasales.application.dto.category.mapper;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.CategorySimpleDto;
import com.iroman.pharmasales.persistence.entity.Category;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class})
public interface CategoryMapper {
    // Dto from Entity start
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "keyword", source = "keyword")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    CategoryDto toCategoryDto(Category category);
    List<CategoryDto> toCategoryDtos(List<Category> categories);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategorySimpleDto toCategorySimpleDto(Category category);
    List<CategorySimpleDto> toCategorySimpleDtos(List<Category> categories);
    // Entity to Dto end

    // Entity from Dto start
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Category toCategory(CategorySaveDto categorySaveDto);
    // Entity from Dto end
}
