package com.scaler.suman.ProductCatalogService1.dtos;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.scaler.suman.ProductCatalogService1.models.Category;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    private String name;
    private String imageUrl;
    private String description;
    private Double price;
    private CategoryDto category;
}
