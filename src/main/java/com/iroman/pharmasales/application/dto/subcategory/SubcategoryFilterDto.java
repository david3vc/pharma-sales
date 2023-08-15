package com.iroman.pharmasales.application.dto.subcategory;

import com.iroman.pharmasales.shared.state.enums.State;
import lombok.Data;

@Data
public class SubcategoryFilterDto {
    private String name;
    private String description;
    private Long categoryId;
    private String state;
}
