package com.alura.literatura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Book;
import com.alura.literatura.model.CategoryLanguage;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Query para listar los libros en la base de datos ordenados por el número de
    // descargas
    @Query("SELECT b FROM Book b ORDER BY b.numDowload DESC")
    List<Book> findAllBooks();

    // Query para listar los autores únicos en la base de datos
    @Query("SELECT DISTINCT a.name FROM Authors a")
    List<String> findAllUniqueAuthorNames();

    // Query para listar los autores vivos en un determinado año
    @Query("SELECT DISTINCT a.name FROM Authors a  WHERE (a.birthYear IS NULL OR a.birthYear <= :year)AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<String> findAuthorsAliveInYear(@Param("year") Integer year);

    // Query para listar los libros por idioma
    @Query("SELECT b FROM Book b WHERE b.categoryLanguage = :language")
    List<Book> findBooksByLanguage(@Param("language") CategoryLanguage language);

    // Query top 10 libros con más descargas ordenados por número de descargas
    @Query("SELECT b FROM Book b ORDER BY b.numDowload DESC LIMIT 10")
    List<Book> findTop10BooksByNumDowloadDesc();

    // Query para buscar autores por su nombre completo o por parte de su nombre
    @Query("SELECT DISTINCT a FROM Authors a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Authors> findAuthorsByName(@Param("name") String name);

    // Query para buscar los libros relacionados con un autor
    @Query(value = "SELECT b.* FROM books b JOIN authors a ON b.id = a.book_id WHERE a.name ~* ('\\m' || :name || '\\M')", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("name") String name);
}