package com.scaler.suman.ProductCatalogService1.services;

import com.scaler.suman.ProductCatalogService1.models.Category;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.repositories.ProductRepo;
import com.scaler.suman.ProductCatalogService1.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class StorageProductService1 implements IProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo; // Essential for handling detached categories

    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isEmpty()) return  null;
        return optionalProduct.get();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        log.info("Creating new product: {}", product.getName());

        // Handle detached Category to prevent PersistentObjectException
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category managedCategory = categoryRepo.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Specified category does not exist"));
            product.setCategory(managedCategory);
        }

        return productRepo.save(product);
    }

    @Override
    @Transactional
    public Product replaceProduct(Long id, Product product) {
        // Ensure we are updating the specific ID requested
        return productRepo.findById(id)
                .map(existingProduct -> {
                    product.setId(id);
                    return productRepo.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Cannot replace non-existent product with id: " + id));
    }
}
