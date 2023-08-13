package com.iroman.pharmasales.application.dto.subcategory.mapper;

import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.mapper.CategoryMapper;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.persistence.entity.Subcategory;
import com.iroman.pharmasales.shared.state.mapper.StateMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {StateMapper.class, CategoryMapper.class})
public interface SubcategoryMapper {
    // Entity to Dto start
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "keyword", target = "keyword")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    SubcategoryDto toSubcategoryDto(Subcategory subcategory);

    List<SubcategoryDto> toSubcategoryDtos(List<Subcategory> subcategories);
    // Entity to Dto end

    // Dto to Entity start
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "keyword", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "state", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Subcategory toSubcateogry(SubcategorySaveDto subcategorySaveDto);

    @InheritConfiguration
    void updateSubcategory(@MappingTarget Subcategory subcategory, SubcategorySaveDto subcategorySaveDto);
    // Dto to Entity end
}
