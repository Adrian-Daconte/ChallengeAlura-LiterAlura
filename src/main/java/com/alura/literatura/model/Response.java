package com.alura.literatura.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.literatura.api.model.ResponseRecord;

public class Response {
    private List<Book> books;

    public Response(ResponseRecord response) {
        this.books = response.books().stream().map(booksRecord -> new Book(booksRecord))
                .collect(Collectors.toList());
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = new ArrayList<>(books) ;
    }

    @Override
    public String toString() {
        return "Response [books=" + books + ", getBooks()=" + getBooks() + "]";
    }

}
