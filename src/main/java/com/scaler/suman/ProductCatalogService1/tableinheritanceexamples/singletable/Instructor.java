package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.singletable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
@Entity(name="st_instructor")
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    String company;
}
