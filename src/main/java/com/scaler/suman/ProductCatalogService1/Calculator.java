package com.scaler.suman.ProductCatalogService1;

public class Calculator {
    int add(int a, int b){
        return a+b;
    }
    int divide(int a, int b){
        try {
            return a/b;
        }catch (ArithmeticException ex){
            throw ex;
        }
    }
}
