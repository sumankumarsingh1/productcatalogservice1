package com.scaler.suman.ProductCatalogService1.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class Utilities {
    public void printObject(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(obj);
        System.out.println(jsonString);
    }

}
