package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.tableperclass;

import jakarta.persistence.Entity;

@Entity(name="tpc_mentor")
public class Mentor extends User{
    private Long hours;
}
