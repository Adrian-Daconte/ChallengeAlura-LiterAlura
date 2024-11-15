package com.alura.literatura.main;

import java.util.List;
import java.util.Scanner;
import org.springframework.dao.DataIntegrityViolationException;
import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.AuthorsRecord;
import com.alura.literatura.api.model.ResponseRecord;
import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Response;
import com.alura.literatura.repository.BookRepository;


public class Main {

    /*
     * Global Variables and instances
     */

    private ApiClient apiClient = new ApiClient();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "http://gutendex.com/books/";
    private JsonConverter jConverter = new JsonConverter();
    private BookRepository repository;
    // private List<BooksRecord> listBooks = new ArrayList<>();

    public Main(BookRepository repository) {
        this.repository=repository;
    }

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
                    listBooksSearch();
                    break;
                case 0:
                    System.out.println("Hasta pronto  , saliendo del programa ...");
                    break;
                default:
            }
        }

    }

    /*
     * Methods of menu
     */
    public void searchBook() {
        System.out.println("Digite el título del libro que desea buscar:");
        var title = scanner.nextLine();
        String url = URL_BASE + "?search=" + title;
        var response = apiClient.getData(url);
        var data = jConverter.converterToJson(response, ResponseRecord.class);
        Response newResponse = new Response(data);
        try {
            newResponse.getBooks().stream().findFirst().ifPresentOrElse(book -> {
                System.out.println(book);
                newResponse.setBooks(List.of(book));
                
                for (Authors authors : book.getAuthors()) {
                    authors.setBook(book);
                }
    
                repository.save(book);
            }, () -> System.out.println("Libro No encontrado"));
        } catch (DataIntegrityViolationException e) {
            System.out.println("\n El Libro ya se encuentra Registrado en la db por favor ingrese otro valor ");
            searchBook();

        }
        
    }

    public void listBooksSearch(){
    }

    

}
