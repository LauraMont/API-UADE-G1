package com.uade.tpo.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.marketplace.entity.Categories;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;
import com.uade.tpo.marketplace.repository.CategoryRepository;

// @Service // define la capa de dependencias del servicio (es una implementación de algo)
// public class CategoryServiceImplementation implements CategoryService{
    // @Autowired
    // private CategoryRepository categoryRepository;

    // public List<Categories> getCategories() {
    //     return categoryRepository.findAll();
    // }

    // public Optional<Categories> getCategoryById(Long categoryId) {
    //     return categoryRepository.findById(categoryId);
    // }

    // public Categories createCategory(String description) throws CategoryDuplicateException {
    //     List<Categories> categories = categoryRepository.findByDescription(description);
    //     if (categories.isEmpty())
    //         return categoryRepository.save(new Categories());
    //     throw new CategoryDuplicateException();
    // }
    
//}
