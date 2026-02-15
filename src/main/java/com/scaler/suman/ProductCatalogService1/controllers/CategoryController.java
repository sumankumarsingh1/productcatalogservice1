package com.scaler.suman.ProductCatalogService1.controllers;

import com.scaler.suman.ProductCatalogService1.dtos.CategoryDto;
import com.scaler.suman.ProductCatalogService1.models.Category;
import com.scaler.suman.ProductCatalogService1.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public CategoryDto getCategoryById(@PathVariable("id") Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        return from(category);
    }

    @PostMapping
    public CategoryDto createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = from(categoryDto);
        return from(categoryService.createCategory(category));
    }

    @PutMapping("{id}")
    public CategoryDto updateCategory(@PathVariable("id") Long categoryId, @RequestBody @Valid CategoryDto categoryDto) {
        Category category = from(categoryDto);
        return from(categoryService.updateCategory(categoryId, category));
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    private Category from(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    private CategoryDto from(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }
}
