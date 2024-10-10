package com.scaler.suman.ProductCatalogService1.controllers;

import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    void Test_getProductById_WithValidId_RetrunProductSuccessfully() {
        // Arrange
        Long id=1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Vanilla Cake");
        //        when(productService.getProductById(any(Long.class)))
        //                .thenReturn(product);
        when(productService.getProductById(id))
                .thenReturn(product);

        // Act
        ResponseEntity<ProductDto> response = productController.getProduct(id);


    }



    @Test
    void getProduct() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void replaceProduct() {
    }

    @Test
    void createProduct() {
    }
}