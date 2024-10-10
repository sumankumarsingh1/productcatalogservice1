package com.scaler.suman.ProductCatalogService1.repositories;

import com.scaler.suman.ProductCatalogService1.models.Category;
import com.scaler.suman.ProductCatalogService1.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;
    @Test
    @Transactional
    void testFetchTypes(){
        Optional<Category> optionalCategory = categoryRepo.findById(3L);
        System.out.println(optionalCategory.get().getName());
        for(Product p: optionalCategory.get().getProductList()){
            System.out.println(p.getName());
        }
    }

    @Test
    @Transactional
    void testFetchTypesAndModes(){
        // Fetch Category
        Optional<Category> optionalCategory = categoryRepo.findById(3L);
        System.out.println(optionalCategory.get().getName());
        // Fetch Products of above Category
        for(Product p: optionalCategory.get().getProductList()){
            System.out.println(p.getName());
        }
    }

    @Test
    @Transactional
    void testSomething(){
        // Fetch All Category
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category: categoryList){
            System.out.println(category.getName());
            List<Product> productList = category.getProductList();
            if(!productList.isEmpty()){
                for(Product p: productList){
                    System.out.println("   "+p.getName());
                }
            }
        }
    }

}