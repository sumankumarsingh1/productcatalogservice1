package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.mappedsuperclass;

import jakarta.persistence.Entity;

@Entity(name="msc_mentor")
public class Mentor extends User {
    private Long hours;
}
