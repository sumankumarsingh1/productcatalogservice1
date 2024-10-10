package com.scaler.suman.ProductCatalogService1.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.scaler.suman.ProductCatalogService1.models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface IProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);
}
