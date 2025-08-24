package com.scaler.suman.ProductCatalogService1.controllers;

import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductControllerFlow {
    @Autowired
    ProductController productController;

    @Autowired
    private IProductService productService;

    @Test
    public void Test_Create_Replace_Get_Product_RunsSuccessfully(){
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(10L);
        productDto.setName("Strawberry Chocolate Cone");
        productDto.setDescription("Strawberry Chocolate Cone Description");
        productDto.setPrice(99.50);

        // Act
        ProductDto response = productController.createProduct(productDto);
        ResponseEntity<ProductDto> responseEntity =  productController.getProductById(response.getId());

        productDto.setName("Vanilla Chocolate Cone");
        productDto.setDescription("Vanilla Chocolate Cone Description");
        productDto.setPrice(91.50);

        ProductDto response2 = productController.replaceProduct(response.getId(),productDto);
        ResponseEntity<ProductDto> responseEntity2 =  productController.getProductById(response2.getId());

        // Assert
        assertEquals("Strawberry Chocolate Cone",responseEntity.getBody().getName());
        assertEquals("Vanilla Chocolate Cone",responseEntity2.getBody().getName());
    }
}
