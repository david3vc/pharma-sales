package com.iroman.pharmasales.persistence.repository;

import com.iroman.pharmasales.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
