package com.alura.literalura.service;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    public JsonNode buscarLivro(String titulo) {

        try {
            String endereco =
                    "https://gutendex.com/books/?search="
                            + titulo.replace(" ", "%20");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(response.body());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consumir a API", e);
        }
    }
}
