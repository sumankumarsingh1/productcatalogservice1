package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.tableperclass;
import jakarta.persistence.Entity;
@Entity(name="tpc_instructor")
public class Instructor extends User{
    String company;
}
