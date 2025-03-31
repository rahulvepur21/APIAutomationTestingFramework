package org.apiframework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class Utility {


    // Generic method to convert JSON response to a POJO
    public static <T> T convertJsonToPojo(Response response, Class<T> pojoClass) {
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response.getBody().asString(), pojoClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON response to POJO: " + e.getMessage());
        }
    }
    
    public static <T> T convertJsonToPojo(String jsonPayload, Class<T> pojoClass) {
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonPayload, pojoClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON response to POJO: " + e.getMessage());
        }
    }
}
