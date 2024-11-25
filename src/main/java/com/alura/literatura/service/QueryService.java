package com.alura.literatura.service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Book;
import com.alura.literatura.model.CategoryLanguage;
import com.alura.literatura.repository.BookRepository;

@Service
public class QueryService {

    // Atributo que se inyectará con el repositorio de libros (BookRepository)
    private final BookRepository bookRepository;

    // Constructor para inyectar el repositorio de libros (Autowired)
    public QueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Implementación del método que lista los libros
    public void listBooks() {
        List<Book> books = bookRepository.findAllBooks();

        System.out.println("""
                \n------------------------
                    LIBROS REGISTRADOS
                ------------------------
                """);

        books.forEach(System.out::println);
    }

    // metodo para listar los autores buscados
    public void listAuthors() {
        List<String> authors = bookRepository.findAllUniqueAuthorNames();

        System.out.println("""
                \n-------------------------
                    AUTORES REGISTRADOS
                -------------------------\n
                """);

        authors.forEach(a -> System.out.println("-- " + a));
    }

    // metodo para listar los autores vivos en un determinado año
    public void listAuthorsAliveInYear(Integer year) {
        List<String> authors = bookRepository.findAuthorsAliveInYear(year);

        System.out.println("""
                \n-----------------------------------
                    AUTORES VIVOS EN EL AÑO %d
                -----------------------------------\n
                """.formatted(year));

        authors.forEach(a -> System.out.println("-- " + a));
    }

    // metodo para listar los libros por idioma
    public void listBooksByLanguage(String languageString) {
        try {
            CategoryLanguage language = CategoryLanguage.valueOf(languageString.toUpperCase());
            List<Book> books = bookRepository.findBooksByLanguage(language);

            System.out.println("""
                    \n-----------------------------------
                        LIBROS EN EL IDIOMA %s
                    -----------------------------------\n
                    """.formatted(language));

            books.forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma no válido: " + languageString);
        }
    }

    // metodo para generar estadísticas de descargas DoubleSumaryStatistics
    public void generateStatistics() {
        List<Book> books = bookRepository.findAllBooks();
        Integer totalBooks = books.size();
        Integer totalDownloads = books.stream().mapToInt(Book::getNumDowload).sum();
        DoubleSummaryStatistics summaryStatistics = books.stream().mapToDouble(Book::getNumDowload).summaryStatistics();

        System.out.println("""
                \n-----------------------------------
                    ESTADÍSTICAS DE DESCARGAS
                -----------------------------------\n
                Total de libros: %d
                Total de descargas: %d
                Promedio de descargas por libro: %.2f
                Mínimo de descargas: %.0f
                Máximo de descargas: %.0f
                """.formatted(totalBooks, totalDownloads, summaryStatistics.getAverage(),
                summaryStatistics.getMin(), summaryStatistics.getMax()));
    }

    // metodo top 10 libros con más descargas
    public void top10BooksByDownloads() {
        List<Book> top10Books = bookRepository.findTop10BooksByNumDowloadDesc();

        System.out.println("""
                \n----------------------------------------
                    TOP 10 LIBROS CON MÁS DESCARGAS
                ----------------------------------------\n
                """);

        top10Books.forEach(System.out::println);
    }

    // metodo para buscar autor por su nombre completo o por parte de su nombre en
    // la db
    public void searchByAuthor(String name) {
        List<Authors> authors = bookRepository.findAuthorsByName(name);
        System.out.println("""
                \n----------------------------------------
                    AUTORES ENCONTRADOS POR :%s
                ----------------------------------------\n
                """.formatted(name));

        authors.forEach(
                a -> System.out.println("""
                        Autor: %s
                        Nacimiento: %d
                        Muerte: %d\n
                        """.formatted(a.getName(), a.getBirthYear(), a.getDeathYear())));
    }

    // metodo para buscar libros por autor
    public void booksByAuthor(String name) {
        List<Book> bookByAuthors = bookRepository.findBooksByAuthor(name);
        System.out.println("""
                \n----------------------------------------
                    LIBROS ENCONTRADOS POR :%s
                ----------------------------------------\n
                """.formatted(name));

        bookByAuthors.forEach(System.out::println);
    }

}
