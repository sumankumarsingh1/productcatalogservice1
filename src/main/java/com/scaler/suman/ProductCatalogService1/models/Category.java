package com.scaler.suman.ProductCatalogService1.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Category extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> productList;
}
