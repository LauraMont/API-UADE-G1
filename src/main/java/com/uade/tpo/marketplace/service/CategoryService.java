package com.uade.tpo.marketplace.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.marketplace.entity.Categories;
import com.uade.tpo.marketplace.exceptions.CategoryDuplicateException;

public interface CategoryService {
    
    public List<Categories> getCategories();

    public Optional<Categories> getCategoryById(Long categoryId);

    public Categories createCategory(String description) throws CategoryDuplicateException;


}
