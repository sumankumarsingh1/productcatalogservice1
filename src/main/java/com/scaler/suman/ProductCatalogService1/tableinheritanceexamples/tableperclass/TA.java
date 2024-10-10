package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class TA extends User{
    private Double rating;
}
