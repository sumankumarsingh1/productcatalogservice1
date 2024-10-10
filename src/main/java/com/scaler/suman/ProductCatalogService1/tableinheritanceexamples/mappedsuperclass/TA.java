package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_ta")
public class TA extends User {
    private Double rating;
}
