package com.iroman.pharmasales.expose.web;

import com.iroman.pharmasales.application.dto.subcategory.SubcategoryDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategoryFilterDto;
import com.iroman.pharmasales.application.dto.subcategory.SubcategorySaveDto;
import com.iroman.pharmasales.application.service.SubcategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/subcategories")
@RequiredArgsConstructor
@RestController
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @GetMapping
    public ResponseEntity<List<SubcategoryDto>> findAll(){
        List<SubcategoryDto> subcategories = subcategoryService.findAll();

        return ResponseEntity.ok(subcategories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryDto> findById(@PathVariable("id") Long id){
        SubcategoryDto subcategory = subcategoryService.findById(id);

        return ResponseEntity.ok(subcategory);
    }

    @PostMapping
    public ResponseEntity<SubcategoryDto> create(@RequestBody SubcategorySaveDto subcategoryBody){
        SubcategoryDto subcategory = subcategoryService.create(subcategoryBody);

        return ResponseEntity.ok(subcategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubcategoryDto> edit(@PathVariable("id") Long id, @RequestBody SubcategorySaveDto subcategoryBody){
        SubcategoryDto subcategory = subcategoryService.edit(id, subcategoryBody);

        return ResponseEntity.ok(subcategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SubcategoryDto> disable(@PathVariable("id") Long id){
        SubcategoryDto subcategory = subcategoryService.disable(id);

        return  ResponseEntity.ok(subcategory);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SubcategoryDto>> filter(Optional<SubcategoryFilterDto> filter){
        List<SubcategoryDto> subcategories = subcategoryService.filter(filter);

        return ResponseEntity.ok(subcategories);
    }
}
