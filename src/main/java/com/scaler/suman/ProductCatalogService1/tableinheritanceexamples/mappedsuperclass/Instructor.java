package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.mappedsuperclass;
import jakarta.persistence.Entity;
@Entity(name="msc_instructor")
public class Instructor extends User {
    String company;
}
