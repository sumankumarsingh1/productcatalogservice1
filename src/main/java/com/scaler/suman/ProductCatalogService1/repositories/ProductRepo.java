package com.scaler.suman.ProductCatalogService1.repositories;

import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.models.State;

import io.micrometer.common.lang.NonNull;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Page<Product> findProductsByName(String query, PageRequest pageable);
    Optional<Product> findById(Long id);
    List<Product> findProductByPriceBetween(Double low, Double high);
    List<Product> findAllByIsPrimeSpecific(Boolean value);
    List<Product> findAllByIsPrimeSpecificTrue();
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findAllByState(State state);
    Product findByState(State state);

    @Query("SELECT p.name from Product p WHERE p.id=?1")
    String findProductNameById(Long id);

    @Query("SELECT c.name from Product p join Category c ON p.category.id=c.id WHERE p.id=:pid")
    String findCategoryNameByProductId(Long pid);

}
