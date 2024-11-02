package com.scaler.suman.ProductCatalogService1.services;

import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Primary
public class StorageProductService implements IProductService{

    @Autowired
    private ProductRepo productRepo;
    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isPresent()) return productOptional.get();
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct = productRepo.save(product);
        return newProduct;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return productRepo.save(product);
    }
}
