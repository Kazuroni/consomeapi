


package br.edu.fatec.emailmkt.services;

import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.io.IOException;

public class consomeapi {
    public String lerjson(String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

}
