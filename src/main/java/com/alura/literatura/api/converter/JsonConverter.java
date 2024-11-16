package com.alura.literatura.api.converter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonConverter implements IJsonConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T converterToJson(String json, Class<T> convToClass) {
        try {
            return objectMapper.readValue(json, convToClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("ERROR : " + e.getMessage());
        }
    }

}
