package com.iroman.pharmasales.expose.web;

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
    ResponseEntity<List<Category>> findAll(){
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    ResponseEntity<Category> findById(@PathVariable("id") Long id){
        Category category = categoryService.findById(id);

        return ResponseEntity.ok(category);
    }

    @PostMapping
    ResponseEntity<Category> create(@RequestBody CategorySaveDto categoryBody){
        Category category = categoryService.create(categoryBody);

        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    ResponseEntity<Category> edit(@PathVariable("id") Long id, @RequestBody CategorySaveDto categoryBody){
        Category category = categoryService.edit(id, categoryBody);

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Category> disable(@PathVariable("id") Long id){
        Category category = categoryService.disable(id);

        return  ResponseEntity.ok(category);
    }
}
