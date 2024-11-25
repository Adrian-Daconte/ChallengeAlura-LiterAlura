package com.alura.literatura.main;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.alura.literatura.model.CategoryLanguage;
import com.alura.literatura.service.QueryService;
import com.alura.literatura.service.SearchBookService;

@Component
public class Main {

    private static final String MENU_OPTIONS = """
            \n---------------------------------------------
            Bienvenido a LiterAlura , nuestras opciones :
            ---------------------------------------------

                    1. Buscar [API]
                    2. Listar Libros [DB]
                    3. Listar Autores [DB]
                    4. Autores vivos en un determinado año [DB]
                    5. Libros por idioma [DB]
                    6. Generar estadisticas [DB]
                    7. Top 10 libros más descargados [DB]
                    8. Buscar Autor por nombre o apellido [DB]
                    9. Buscar los libros de un autor [DB]

                    0. Salir \n
            """;

    private static final String LANGUAGE_OPTIONS = """
            Seleccione el idioma de los libros a  listar:

                1- Inglés (INGLES)
                2- Español (ESPANOL)
                3- Francés (FRANCES)
                4- Alemán (ALEMAN)
                5- Italiano (ITALIANO)
                6- Ruso (RUSO)
            """;

    // Se inyecta los servicios en el constructor para inyectarlos en esta clase
    private final SearchBookService searchBookService;
    private final QueryService queryService;

    // Constructor para inyectar los servicios en esta clase
    public Main(SearchBookService searchBookService, QueryService queryService) {
        this.searchBookService = searchBookService;
        this.queryService = queryService;
    }

    public void menu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            do {
                System.out.println(MENU_OPTIONS);
                option = getValidOption(scanner, 0, 9);
                processOption(option, scanner);
            } while (option != 0);
        }
    }

    private int getValidOption(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int option = Integer.parseInt(scanner.nextLine().trim());
                if (option >= min && option <= max) {
                    return option;
                }
                System.out.printf("Opción fuera de rango. Por favor, elija entre %d y %d.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private void processOption(int option, Scanner scanner) {
        switch (option) {
            case 1 -> searchBookService.searchMethod();
            case 2 -> queryService.listBooks();
            case 3 -> queryService.listAuthors();
            case 4 -> {
                System.out.println("Ingrese el año para listar los autores vivos:");
                int year = getValidYear(scanner);
                queryService.listAuthorsAliveInYear(year);
            }
            case 5 -> processLanguageOption(scanner);
            case 6 -> queryService.generateStatistics();
            case 7 -> queryService.top10BooksByDownloads();
            case 8 -> {
                System.out.println("\nIngrese el nombre del autor  o apellidos:\n");
                String name = scanner.nextLine().trim();
                queryService.searchByAuthor(name);
            }
            case 9 -> {
                System.out.println("\nIngrese el nombre del autor  o apellidos:\n");
                String name = scanner.nextLine().trim();
                queryService.booksByAuthor(name);
            }

            case 0 -> System.out.println("\nHasta pronto, saliendo del programa...\n");
        }
    }

    private int getValidYear(Scanner scanner) {
        int currentYear = LocalDate.now().getYear();
        while (true) {
            try {
                int year = Integer.parseInt(scanner.nextLine().trim());
                if (year > 0 && year <= currentYear) {
                    return year;
                }
                System.out.println("Por favor, ingrese un año válido (mayor que 0).");
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido para el año.");
            }
        }
    }

    private void processLanguageOption(Scanner scanner) {
        System.out.println(LANGUAGE_OPTIONS);
        int languageOption = getValidOption(scanner, 1, 5);
        CategoryLanguage language = switch (languageOption) {
            case 1 -> CategoryLanguage.INGLES;
            case 2 -> CategoryLanguage.ESPAÑOL;
            case 3 -> CategoryLanguage.FRANCES;
            case 4 -> CategoryLanguage.ALEMAN;
            case 5 -> CategoryLanguage.ITALIANO;
            default -> throw new IllegalStateException("Opción de idioma no válida: " + languageOption);
        };
        queryService.listBooksByLanguage(language.name());
    }
}