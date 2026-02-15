package com.scaler.suman.ProductCatalogService1.controllers;
import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.exceptions.ProductNotFoundException;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.services.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import com.scaler.suman.ProductCatalogService1.dtos.CategoryDto;
import com.scaler.suman.ProductCatalogService1.models.Category;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        logger.info("Getting product by id: {}", productId);
        try {
            if (productId < 1 || productId > 20) {
                throw new ProductNotFoundException("Product not present!");
            }
            Product product = productService.getProductById(productId);
            if (product == null) {
                throw new ProductNotFoundException("Product not found");
            }
            ProductDto productDto = from(product);
            HttpHeaders headers = new HttpHeaders();
            headers.add("called by", "smart frontend");
            return ResponseEntity.ok().headers(headers).body(productDto);
        } catch (ProductNotFoundException ex) {
            logger.error("Error retrieving product: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            logger.error("Error retrieving product: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            if (products == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            List<ProductDto> productDtos = products.stream()
                    .map(this::from)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(productDtos);
        } catch (Exception ex) {
            logger.error("Error retrieving products: {}", ex.getMessage(), ex);
            throw ex;
        }
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto){
        Product product = from(productDto);
        Product replacedProduct = productService.replaceProduct(productId,product);
        return from(replacedProduct);
    }
    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = from(productDto);
        try{
            System.out.println(new ObjectMapper().writeValueAsString(product) );
        }catch (Exception e){}
        return from(productService.createProduct(product));
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription((productDto.getDescription()));
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if(productDto.getCategory()!=null){
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setName(productDto.getCategory().getName());
            category.setDescription(productDto.getCategory().getDescription());
            product.setCategory(category);
        }
        return product;
    }
    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }
        return productDto;
    }
}
