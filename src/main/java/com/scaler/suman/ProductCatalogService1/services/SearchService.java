package com.scaler.suman.ProductCatalogService1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.scaler.suman.ProductCatalogService1.repositories.ProductRepo;
import com.scaler.suman.ProductCatalogService1.dtos.SortParam;
import com.scaler.suman.ProductCatalogService1.dtos.SortType;
import com.scaler.suman.ProductCatalogService1.models.Product;

@Service
public class SearchService {
    @Autowired
    private ProductRepo productRepo;

    @SuppressWarnings("null")
    public Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize, List<SortParam> sortParams) {
        return productRepo.findProductsByName(query, PageRequest.of(pageNumber, pageSize, getSort(sortParams)));
    }

    private Sort getSort(List<SortParam> sortParams) {
        Sort sort = Sort.unsorted();
        for (SortParam sortParam : sortParams) {
            if (sortParam.getSortType().equals(SortType.ASC)) {
                sort = sort.and(Sort.by(sortParam.getParamName()));
            } else {
                sort = sort.and(Sort.by(sortParam.getParamName()).descending());
            }
        }
        return sort;
    }
}

