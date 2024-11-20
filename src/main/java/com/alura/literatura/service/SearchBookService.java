package com.alura.literatura.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.ResponseRecord;
import com.alura.literatura.model.Book;
import com.alura.literatura.repository.BookRepository;

@Service

public class SearchBookService {
    @Autowired
    private ApiClient apiClient;
    @Autowired
    private JsonConverter jConverter;
    @Autowired
    private BookRepository bookRepository;


    Scanner scanner = new Scanner(System.in);
    @Value("${app.api.key}")
    private String URL_BASE;

   public void searchMethod() {
    while (true) {
        System.out.println("\nDigite el título del libro que desea buscar (o 'salir' para terminar): \n");
        String title = scanner.nextLine().trim();

        if (title.equalsIgnoreCase("salir")) {
            System.out.println("\nSaliendo de la búsqueda...\n");
            return;
        }

        if (title.isEmpty()) {
            System.out.println("\nERROR: No se admiten valores vacíos. Intente nuevamente.\n");
            continue;
        }

        String url = URL_BASE + "?search=" + title.replaceAll(" ", "%20");
        System.out.println("\nConsultando API ...\n"+"Por favor, espere...\n");

        try {
            var response = apiClient.getData(url);
            var data = jConverter.converterToJson(response, ResponseRecord.class);
            
            var matchingBook = data.books().stream()
                .filter(b -> b.title().toLowerCase().contains(title.toLowerCase()))
                .findFirst();

            if (matchingBook.isPresent()) {
                var book = new Book(matchingBook.get());
                System.out.println("\n(✓) LIBRO ENCONTRADO:\n" + book + "\n");
                book.getAuthors().forEach(author -> author.setBook(book));
                bookRepository.save(book);
                System.out.println("\n(✓) Libro guardado en la base de datos.\n");
                return;
            } else {
                System.out.println("\n[X] Libro no encontrado en la API. Por favor, intente nuevamente.\n");
            }
        } catch (DataIntegrityViolationException e) {
            System.out.println("\n[X] Libro ya registrado anteriormente. Intente con otro título.\n");
        } catch (Exception e) {
            System.out.println("\n[X] ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage() + "Intente nuevamente con otro titulo\n");
        }
    }
}

}
