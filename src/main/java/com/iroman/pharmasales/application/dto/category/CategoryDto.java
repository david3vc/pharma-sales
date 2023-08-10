package com.iroman.pharmasales.application.dto.category;

import com.iroman.pharmasales.shared.state.enums.State;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String keyword;
    private State state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
