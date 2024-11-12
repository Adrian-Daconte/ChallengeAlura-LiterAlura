package com.alura.literatura.api.converter;

public interface IJsonConverter {
    <T> T converterToJson(String json, Class<T> convToClass);
}
