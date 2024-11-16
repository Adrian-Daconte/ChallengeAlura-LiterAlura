package com.alura.literatura.service;

import java.util.List;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.ResponseRecord;
import com.alura.literatura.model.Authors;
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
        System.out.println("Digite el título del libro que desea buscar:");
        Scanner scanner = new Scanner(System.in);
        var title = scanner.nextLine();
        scanner.close();
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

                bookRepository.save(book);
            }, () -> System.out.println("Libro No encontrado"));
        } catch (DataIntegrityViolationException e) {
            System.out.println(
                    "\n El Libro ya se encuentra Registrado en la db por favor ingrese otro valor ");
            searchMethod();

        }


    }
}
