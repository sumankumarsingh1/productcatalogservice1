package com.scaler.suman.ProductCatalogService1.repositories;

import com.scaler.suman.ProductCatalogService1.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
