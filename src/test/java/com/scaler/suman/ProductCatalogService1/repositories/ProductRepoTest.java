package com.scaler.suman.ProductCatalogService1.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void testJpaQuery(){
        // List<Product> products = productRepo.findProductByPriceBetween(200.0,400.0);
        // List<Product> products1 = productRepo.findAllByIsPrimeSpecific(false);
        // List<Product> products2 = productRepo.findAllByIsPrimeSpecificTrue();
        // List<Product> products3 = productRepo.findAllByState(State.INACTIVE);
        // List<Product> products4 = productRepo.findAllByOrderByPriceDesc();
        System.out.println(productRepo.findProductNameById(3L));
        System.out.println(productRepo.findCategoryNameByProductId(1L));
    }

}