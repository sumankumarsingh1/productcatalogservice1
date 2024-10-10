package com.scaler.suman.ProductCatalogService1.tableinheritanceexamples.joinedclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="jc_mentor")
@PrimaryKeyJoinColumn(name="user_id")
public class Mentor extends User {
    private Long hours;
}
