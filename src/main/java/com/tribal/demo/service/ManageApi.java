package com.tribal.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribal.demo.Domain.JokeDomain;
import com.tribal.demo.service.interfaces.GetData;
import com.tribal.demo.utils.Constants;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
public class ManageApi implements GetData {
    public JokeDomain getApiRequest() throws Exception {

        HttpClient client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(500))
                .build();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(Constants.endpoint))
                .header("Content-Type", "text/plain")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.printf("Status %s \n", response.statusCode());
        System.out.printf("Response %s \n", response.body());

        ObjectMapper objectMapper = new ObjectMapper();
        JokeDomain joke = objectMapper.readValue(response.body(), JokeDomain.class);
        System.out.println(joke);

        return joke;

    }

    public JokeDomain getApiRequestAsync() {
        ObjectMapper objectMapper = new ObjectMapper();
        JokeDomain joke;
        HttpClient client = HttpClient
                .newBuilder()
                .connectTimeout(Duration.ofMillis(1000))
                .build();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(Constants.endpoint))
                .header("Content-Type", "text/plain")
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        try {
            String result = future.thenApply(HttpResponse::body).get(5000, TimeUnit.SECONDS);
            joke = objectMapper.readValue(result, JokeDomain.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return joke;

    }

    @Override
    public JokeDomain execute() {
        return getApiRequestAsync();
    }
}
