package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.category.CategoryDto;
import com.iroman.pharmasales.application.dto.category.CategorySaveDto;
import com.iroman.pharmasales.application.service.CategoryService;
import com.iroman.pharmasales.persistence.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RequiredArgsConstructor
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    // public CategoryController(CategoryRepository categoryRepository){
        // this.categoryRepository = categoryRepository;
    // }

    @GetMapping
    ResponseEntity<List<CategoryDto>> findAll(){
        List<CategoryDto> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id){
        CategoryDto category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    ResponseEntity<CategoryDto> create(@RequestBody CategorySaveDto categoryBody){
        CategoryDto category = categoryService.create(categoryBody);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    ResponseEntity<CategoryDto> edit(@PathVariable("id") Long id, @RequestBody CategorySaveDto categoryBody){
        CategoryDto category = categoryService.edit(id, categoryBody);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<CategoryDto> disable(@PathVariable("id") Long id){
        CategoryDto category = categoryService.disable(id);

        return  ResponseEntity.ok(category);
    }
}
