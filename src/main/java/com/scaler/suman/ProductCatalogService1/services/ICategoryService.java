package com.scaler.suman.ProductCatalogService1.services;

import com.scaler.suman.ProductCatalogService1.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Long categoryId, Category category);
    void deleteCategory(Long categoryId);
}
