package com.scaler.suman.ProductCatalogService1.services;

import com.scaler.suman.ProductCatalogService1.exceptions.CategoryNotFoundException;
import com.scaler.suman.ProductCatalogService1.models.Category;
import com.scaler.suman.ProductCatalogService1.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
        if(optionalCategory.isEmpty()) return null;
        return optionalCategory.get();
    }

    @Override
    public Category createCategory(Category category) {
        return null;
    }

    @Override
    public Category updateCategory(Long categoryId, Category category) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }
}
