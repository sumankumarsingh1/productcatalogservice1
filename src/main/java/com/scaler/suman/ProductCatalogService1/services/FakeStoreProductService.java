package com.scaler.suman.ProductCatalogService1.services;

import com.scaler.suman.ProductCatalogService1.client.FakeStoreAPIClient;
import com.scaler.suman.ProductCatalogService1.dtos.FakeStoreProductDto;
import com.scaler.suman.ProductCatalogService1.models.Category;
import com.scaler.suman.ProductCatalogService1.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class FakeStoreProductService implements IProductService {
    @Autowired
    private FakeStoreAPIClient fakeStoreAPIClient;
    @Override
    public Product getProductById(Long id){
        return from(fakeStoreAPIClient.getProductById(id));
    }
    @Override
    public List<Product> getAllProducts(){
        List<Product> products = new ArrayList<>();
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreAPIClient.getAllProducts();
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(from(fakeStoreProductDto));
        }
        return products;
    }
    @Override
    public Product replaceProduct(Long id, Product product){
        return from(fakeStoreAPIClient.replaceProduct(id,from(product)));
    }
    @Override
    public Product createProduct(Product product){
        return from(fakeStoreAPIClient.createProduct(from(product)));
    }
    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product =  new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory()!=null){
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }
}
