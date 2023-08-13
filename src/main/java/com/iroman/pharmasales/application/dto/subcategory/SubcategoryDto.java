package com.iroman.pharmasales.application.dto.subcategory;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.dto.category.CategorySimpleDto;
import com.iroman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubcategoryDto {
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private CategorySimpleDto category;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
