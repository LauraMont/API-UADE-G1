package com.uade.tpo.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.Categories;
import com.uade.tpo.marketplace.entity.dto.CategoryRequest;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.service.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController //anotation que indica que es capa de tráfico HTTP 
@RequestMapping ("categories") // URIs -> localhost:puerto/endpoint
public class CategoriesController {
    @Autowired //con esta anotation se delega a sprint que cuando llegue una request a la capa de tráfico inyecte la dependencia que se necesita
    private CategoryService categoryService;
    
    @GetMapping // localhost:puerto/categories (GET)
    public ResponseEntity<List<Categories>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{categoryId}") // localhost:puerto/categories/1 (GET) es un path variable, va entre llaves
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long categoryId) {
        Optional<Categories> result = categoryService.getCategoryById(categoryId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping // localhost:puerto/categories (POST)
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest)
            throws CategoryDuplicateException {
        Categories result = categoryService.createCategory(categoryRequest.getDescription());
        return ResponseEntity.created(URI.create("/categories/" + result.getId())).body(result);
    }
    
}
