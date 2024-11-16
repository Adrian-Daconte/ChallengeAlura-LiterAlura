package com.alura.literatura.service;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.ResponseRecord;
import com.alura.literatura.model.Response;
import com.alura.literatura.repository.BookRepository;

@Service
public class SearchBookService {
    @Autowired
    private ApiClient apiClient;
    @Autowired
    private JsonConverter jConverter;
    @Autowired
    private BookRepository bookRepository;

    @Value("${app.api.key}")
    private String URL_BASE;

    public void searchMethod() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite el título del libro que desea buscar:");

        String title = scanner.nextLine();

        String url = URL_BASE + "?search=" + title;
        url = url.replaceAll(" ", "%20");

        // Manejamos Excepciones en la API una de los casos: URISyntaxException ,
        // DataIntegrityViolationException
        try {

            // Valida que el título no sea vacío
            if (title.equals("")) {
                System.out
                        .println("\n ERROR : No se admiten valores vacios , intenta nuevamente \n");
                searchMethod();
            }

            // Obtiene los datos del libro de la API
            var response = apiClient.getData(url);
            var data = jConverter.converterToJson(response, ResponseRecord.class);
            Response newResponse = new Response(data);

            newResponse.getBooks().stream().filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .findFirst().ifPresentOrElse(books -> {
                        System.out.println("\n LIBRO ENCONTRADO: " + books + "\n");
                        newResponse.setBooks(List.of(books));

                        books.getAuthors().forEach(authors -> authors.setBook(books));

                        bookRepository.save(books);

                    }, () -> {
                        System.out.println(
                                "\n Libro no encontrado en la API. Por favor, intente nuevamente.\n");
                        searchMethod();

                    });

        } catch (URISyntaxException | IllegalArgumentException e) {
            System.out
                    .println("\n ERROR : Busque un titulo valido  " + title + " : No es valido \n");
        } catch (DataIntegrityViolationException e) {
            System.out.println(
                    "\n El Libro ya se encuentra Registrado en la db por favor ingrese otro valor  "
                            + title + " : No es valido \n");
            searchMethod();
        } catch (Exception e) {
            System.out.println("\n ERROR : " + e.getClass() + "\n");
            searchMethod();
        }

    }
}
