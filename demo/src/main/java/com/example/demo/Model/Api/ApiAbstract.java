package com.example.demo.Model.Api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ApiAbstract implements Api{

    public JsonNode getJson(HttpRequest request) {
        try {
            HttpResponse<String>  response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            return new ObjectMapper().readTree(response.body());

            } catch (IOException e) {
                System.out.println("Error in the request");
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Error in the request");
            }
        return null;
    }

    public abstract String getURL(String countryName);

    @Override
    public HttpRequest callApi(String countryName) {
        try {
            return HttpRequest.newBuilder()
                    .uri(URI.create(cleanURL(this.getURL(countryName))))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
        } catch (Exception e) {
            System.out.println("Error in the request" + e.getMessage());
        }
        return null;
    }

    @Override
    public String cleanURL(String url) {
        return url.replaceAll(" ", "%20");
    }
    
}
