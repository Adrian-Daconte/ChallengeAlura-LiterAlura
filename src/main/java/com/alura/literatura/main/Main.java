package com.alura.literatura.main;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.alura.literatura.service.QueryService;
import com.alura.literatura.service.SearchBookService;

@Component
public class Main {

    private final SearchBookService searchBookService;
    private final QueryService qService;

    // Constructor injectado con la dependencia de BookRepository
    public Main(SearchBookService searchBookService, QueryService qService) {
        this.searchBookService = searchBookService;
        this.qService = qService;
    }

    public void menu() {
        final String MENU_OPTIONS = """
                \n---------------------------------------------
                Bienvenido a LiterAlura , nuestras opciones :
                ---------------------------------------------

                        1. Buscar
                        2. Listar Books
                        3. Listar Autores
                        0. Salir \n
                        """;

        try (Scanner scanner = new Scanner(System.in)) {
            int opcion = -1;

            // Muestra el menú y espera una opción
            do {
                System.out.println(MENU_OPTIONS);

                try {
                    opcion = scanner.nextInt();
                    if (opcion < 0 || opcion > 5) {
                        System.out.println("Opción fuera de rango. Por favor, elija entre 0 y 5.");
                    } else {
                        switch (opcion) {
                            case 1:
                                searchBookService.searchMethod();
                                break;
                            case 2:
                                qService.listBooks();
                                break;
                            case 3:
                                qService.listAuthors();
                                break ;   
                            case 0:
                                System.out.println("Hasta pronto, saliendo del programa...");
                                break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("\n Por favor, ingrese un número válido.\n");
                    scanner.nextLine().trim(); // Limpia el buffer de entrada
                }

            } while (opcion != 0);
        }
    }

}