package com.iroman.pharmasales.persistence.repository;

import com.iroman.pharmasales.persistence.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {
    List<Category> findByState(String state);
}
