package com.scaler.suman.ProductCatalogService1.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchRequestDto {
    private String query;
    private Integer pageSize;
    private Integer pageNumber;
    List<SortParam> sortParams;
}
