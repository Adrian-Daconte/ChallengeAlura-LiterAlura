package com.alura.literatura.service;

import java.util.List;
import java.util.Scanner;
import org.springframework.dao.DataIntegrityViolationException;
import com.alura.literatura.api.client.ApiClient;
import com.alura.literatura.api.converter.JsonConverter;
import com.alura.literatura.api.model.ResponseRecord;
import com.alura.literatura.model.Authors;
import com.alura.literatura.model.Response;
import com.alura.literatura.repository.BookRepository;

public  class searchBook {
    
    private ApiClient apiClient = new ApiClient();
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "http://gutendex.com/books/";
    private JsonConverter jConverter = new JsonConverter();
    private BookRepository repository;

    

public void searchMethod(){
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
            searchMethod();

        }


}
}
