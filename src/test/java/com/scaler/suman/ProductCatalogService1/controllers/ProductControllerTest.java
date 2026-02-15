package com.scaler.suman.ProductCatalogService1.controllers;

import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.services.IProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Captor
    ArgumentCaptor<Long> idCaptor;


    @Test
    void Test_getProductById_WithValidId_RetrunProductSuccessfully() {
        // Arrange
        Long id=1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Vanilla Cake");
        when(productService.getProductById(any(Long.class)))
                .thenReturn(product);

        // Act
        ResponseEntity<ProductDto> response = productController.getProductById(id);

        // Assert
        assertNotNull(response.getBody());
        assertEquals(id,response.getBody().getId());
        assertEquals("Vanilla Cake",response.getBody().getName());

    }

    @Test
    @DisplayName("Parameter 0 resulted in product not present exception")
    void Test_getProductById_WithInValidId_ThrowsException() {
        // Act and Assert
        Exception ex =
        assertThrows(IllegalArgumentException.class,
                ()-> productController.getProductById(0L));
        assertEquals("Product not present!",ex.getMessage() );

        verify(productService,times(0)).getProductById(0L);
    }

    @Test
    void Test_getProductById_ProductService_ThrowsException() {
        // Arrange
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("Something went Bad"));

        // Act and Assert
        assertThrows(RuntimeException.class,()-> productController.getProductById(1L));
    }

    @Test
    void Test_GetProductById_CheckIfCalledWithExpectedArguments(){
        // Arrange
        Long id = 1L;
        Product product =  new Product();
        product.setId(id);
        product.setName("Donut Truffles");
        product.setDescription("Donut Truffles Description");
        product.setPrice(129.50);

        when(productService.getProductById(any(Long.class)))
                .thenReturn(product);

        // Act
        productController.getProductById(id);

        // Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(id,idCaptor.getValue());
    }
}



//    @Test
//    void getProduct() {
//    }
//
//    @Test
//    void getAllProducts() {
//    }
//
//    @Test
//    void replaceProduct() {
//    }
//
//    @Test
//    void createProduct() {
//    }
