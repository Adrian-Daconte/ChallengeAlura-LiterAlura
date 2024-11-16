package com.alura.literatura.main;

import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.alura.literatura.repository.BookRepository;
import com.alura.literatura.service.SearchBookService;

@Component
public class Main {

    private final SearchBookService searchBookService;
    private final BookRepository repository;
    private final Scanner scanner;

    
    public Main(SearchBookService searchBookService, BookRepository repository) {
        this.searchBookService = searchBookService;
        this.repository = repository;
        this.scanner = new Scanner(System.in);
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menuPrint = """

                    Bienvenido a LiterAlura , nuestras opciones :

                    1. Buscar
                    2. Listar
                    0. Salir
                    """;

            System.out.println(menuPrint);

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 0 || opcion > 2) {
                    System.out.println("Opción fuera de rango. Por favor, elija entre 0 y 2.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    searchBookService.searchMethod();
                    break;
                case 2:
                    listBooksSearch();
                    break;
                case 0:
                    System.out.println("Hasta pronto, saliendo del programa...");
                    break;
            }
        }
    }

    private void listBooksSearch() {
        // Implementa la lógica para listar los libros aquí
        System.out.println("Listando libros...");
        // Por ejemplo:
        // repository.findAll().forEach(System.out::println);
    }
}