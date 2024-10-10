package com.scaler.suman.ProductCatalogService1.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {
    @GetMapping("/")
    public static String welcome(){
        return "Welcome to Spring Boot!";
    }
}
