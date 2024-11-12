package com.alura.literatura.api.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataJsonRecord(@JsonAlias("results") List<BooksRecord> books) {

    @Override
    public String toString() {
        return books.toString();
    }


}
