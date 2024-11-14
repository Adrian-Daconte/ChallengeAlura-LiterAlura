package com.alura.literatura.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDataDTO {
    private List<BookDTO> books;

    public BookDataDTO() {
        this.books = new ArrayList<>();
    }

    public void addBook(BookDTO book) {
        this.books.add(book);
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    // Método de utilidad para obtener información específica
    public List<String> getBooksWithAuthorsInfo() {
        List<String> info = new ArrayList<>();
        for (BookDTO book : books) {
            StringBuilder sb = new StringBuilder();
            sb.append("Libro: ").append(book.getTitle()).append(", ");
            for (AuthorDTO author : book.getAuthors()) {
                sb.append("Autor: ").append(author.getName()).append(" (Nacimiento: ")
                        .append(author.getBirthYear()).append("), ");
            }
            info.add(sb.toString());
        }
        return info;
    }
}
