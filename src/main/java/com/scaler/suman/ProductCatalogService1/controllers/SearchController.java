package com.scaler.suman.ProductCatalogService1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scaler.suman.ProductCatalogService1.dtos.ProductDto;
import com.scaler.suman.ProductCatalogService1.models.Product;
import com.scaler.suman.ProductCatalogService1.dtos.SearchRequestDto;
import com.scaler.suman.ProductCatalogService1.dtos.SortParam;
import com.scaler.suman.ProductCatalogService1.services.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto){
        String query = searchRequestDto.getQuery();
        Integer pageNumber = searchRequestDto.getPageNumber();
        Integer pageSize =  searchRequestDto.getPageSize();
        List<SortParam> sortParams = searchRequestDto.getSortParams(); 
        return searchService.searchProducts(query, pageNumber, pageSize,sortParams);
    } 
}
