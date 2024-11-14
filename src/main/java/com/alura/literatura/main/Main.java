package com.alura.literatura.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.BooksRecord;
import com.alura.literatura.api.model.DataJsonRecord;
import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Book;
import com.alura.literatura.dto.BookDataDTO;
import com.alura.literatura.dto.BookDTO;
import com.alura.literatura.mapper.ApiDataMapper;


public class Main {

    /*
     * Global Variables and instances
     */

    private ApiClient apiClient = new ApiClient();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "http://gutendex.com/books/";
    private JsonConverter jConverter = new JsonConverter();
    // private List<BooksRecord> listBooks = new ArrayList<>();
    private List<Book> book = new ArrayList<>();
    private List<Authors> authors = new ArrayList<>();
    private BookDataDTO bookDataDTO;

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menuPrint = """

                    Bienvenido a LiterAlura , nuestras opciones :

                    1.Buscar
                    2. Listar
                    0. Salir
                    """;

            System.out.println(menuPrint);

            var input = scanner.nextLine();

            try {
                opcion = Integer.parseInt(input);
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Opción fuera de rango. Por favor, elija entre 0 y 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }


            switch (opcion) {
                case 1:
                    searchBook();
                    break;

                case 2:
                    listBooks();
                    break;
                case 0:
                    System.out.println("Hasta pronto  , saliendo del programa ...");
                    break;
                default:
            }
        }

    }

    /*
     * Methods
     */

    /*
     * public List<BooksRecord> searchBook() {
     * System.out.println("Digital nombre del libro o autor del libro "); var nameBook =
     * scanner.nextLine(); var url = URL_BASE + "?search=" + nameBook.replace(" ", "%20"); var json
     * = apiClient.getData(url); var data = jConverter.converterToJson(json, DataJsonRecord.class);
     * data.books().stream().findFirst().ifPresentOrElse(book ->{ listBooks.add(book); },
     * ()->System.out.println("No se encontro el libro ")); System.out.println(listBooks.getLast());
     * return listBooks; }
     */
    /*
     * public void searchBook() { System.out.println("Digital nombre del libro o autor del libro ");
     * var nameBook = scanner.nextLine(); var url = URL_BASE + "?search=" + nameBook.replace(" ",
     * "%20"); var json = apiClient.getData(url); var data = jConverter.converterToJson(json,
     * DataJsonRecord.class); //Se genera un ifElse de stream para determinar si existe un libro y
     * que tome el primer valor data.books().stream().findFirst().ifPresentOrElse(books -> {
     * book.add(new Book(books));
     * 
     * }, ()->System.out.println("no se encontro ningun libro ")); System.out.println(book); }
     */


    public void searchBook() {
        System.out.println("Digite el nombre del libro o autor del libro ");
        var nameBook = scanner.nextLine();
        var url = URL_BASE + "?search=" + nameBook.replace(" ", "%20");
        var json = apiClient.getData(url);
        var dataJsonRecord = jConverter.converterToJson(json, DataJsonRecord.class);

        bookDataDTO = ApiDataMapper.mapToBookDataDTO(dataJsonRecord);

        if (!bookDataDTO.getBooks().isEmpty()) {
            BookDTO firstBook = bookDataDTO.getBooks().get(0);
            System.out.println("Libro encontrado:");
            System.out.println("Autores:");
            firstBook.getAuthors().stream().map(l -> l.getName()).forEach(System.out::println);
        } else {
            System.out.println("No se encontró ningún libro");
        }
    }

    // Listar libros
    public void listBooks() {
        System.out.println("Listado de libros : ");

    }
}
