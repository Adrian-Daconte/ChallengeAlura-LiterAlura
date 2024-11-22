package com.alura.literatura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    // query para listar los libros en la base de datos
    @Query(value = "SELECT * FROM books", nativeQuery = true)
    List<Book> booksRegList();

    // query para listar los autores en la base de datos
    @Query("select DISTINCT a from Authors a LEFT JOIN FETCH a.book")
    List<Authors> authorsRegList();

    @Query("SELECT DISTINCT a FROM Authors a")
    List<Authors> findAllAuthors();

    @Query("SELECT DISTINCT a.name FROM Authors a")
List<String> findAllUniqueAuthorNames();
}
