package com.alura.literatura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.literatura.model.Book;
import com.alura.literatura.repository.BookRepository;

@Service
public class QueryService {

    @Autowired
    private BookRepository bookRepository;

    // Implementación del método que lista los libros
    public void listBooks() {
        List<Book> books = bookRepository.booksRegList();

        System.out.println("""
                \n------------------------
                    LIBROS REGISTRADOS
                ------------------------
                """);

        books.stream().forEach(l -> System.out.println(l));
    }

    // metodo para listar los autores buscados
    public void listAuthors() {
        List<String> authors = bookRepository.findAllUniqueAuthorNames();

        System.out.println("""
                \n-------------------------
                    AUTORES REGISTRADOS
                -------------------------\n
                """);

        authors.stream().forEach(a -> System.out.println("""
                -- %s
                """.formatted(a)));

        /*
         * List<Authors> authors = bookRepository.authorsRegList();
         * authors.stream().forEach(a -> System.out.println("""
         * -- %s
         * """.formatted(a)));
         */
    }

}
