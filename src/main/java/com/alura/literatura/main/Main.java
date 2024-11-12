package com.alura.literatura.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.BooksRecord;
import com.alura.literatura.api.model.DataJsonRecord;

public class Main {

    /*
     * Global Variables and instances
     */

    private ApiClient apiClient = new ApiClient();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "http://gutendex.com/books/";
    private JsonConverter jConverter = new JsonConverter();
    private List<BooksRecord> listBooks = new ArrayList<>();

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

    public List<BooksRecord> searchBook() {
        System.out.println("Digital nombre del libro o autor del libro ");
        var nameBook = scanner.nextLine();
        var url = URL_BASE + "?search=" + nameBook.replace(" ", "%20");
        var json = apiClient.getData(url);
        var data = jConverter.converterToJson(json, DataJsonRecord.class);
        data.books().stream().findFirst().ifPresentOrElse(book ->{
            listBooks.add(book);
        }, ()->System.out.println("No se encontro el libro "));
        System.out.println(listBooks.getLast());
        return listBooks;
    }
}
