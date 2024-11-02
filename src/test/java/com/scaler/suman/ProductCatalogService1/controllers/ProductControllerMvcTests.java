package com.scaler.suman.ProductCatalogService1.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductController productController;

    @Test
    public void getAllProducts_Runs_Successfully() throws Exception {
        // Arrange
        List<Product> productList = new ArrayList<>();
        Product product1 =  new Product();
        product1.setId(1L);
        product1.setName("Vanilla IceCream");
        product1.setPrice(56.50);
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Chocolate IceCream");
        product2.setPrice(66.50);
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts())
                .thenReturn(productList);

        // Act and Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productList)))
                .andExpect(jsonPath("$[0].name").value("Vanilla IceCream"))
                .andExpect(jsonPath("$[0].length()").value(3))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void Test_CreateProduct_RunSuccessfully() throws Exception{
        // Arrange
        ProductDto productDto = new ProductDto();
        productDto.setId(5L);
        productDto.setName("Dark Forest IceCream");
        productDto.setDescription("Dark Forest IceCream Description");
        productDto.setPrice(230.40);

        Product product = new Product();
        product.setId(5L);
        product.setName("Dark Forest IceCream");
        product.setDescription("Dark Forest IceCream Description");
        product.setPrice(230.40);
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        // Act and Assert
        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(productDto)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDto)))
                .andExpect(jsonPath("$.name").value("Dark Forest IceCream"))
                .andExpect(jsonPath("$.length()").value(4));
    }
}
