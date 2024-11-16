package com.alura.literatura.api.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Component;

/* 
 * ApiCliente es una clase que se encarga de consumir la API 
 */
@Component
public class ApiClient {
    public String getData(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException("ERROR : "+e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR :"+e.getMessage());
        }
        String json = response.body();
        return json;
    }
}
