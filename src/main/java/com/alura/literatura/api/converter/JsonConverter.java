package com.alura.literatura.api.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
