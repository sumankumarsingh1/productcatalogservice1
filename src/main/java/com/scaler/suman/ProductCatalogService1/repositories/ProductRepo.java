package com.scaler.suman.ProductCatalogService1.repositories;

import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.nio.DoubleBuffer;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
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
