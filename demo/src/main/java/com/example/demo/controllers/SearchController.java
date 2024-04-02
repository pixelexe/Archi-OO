package com.example.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@RestController
public class SearchController {

    @GetMapping("/searchByCapital")
    public String searchCountry(@RequestParam("name") String pays) throws JsonMappingException, JsonProcessingException { //Besoin que ce soit un @RestController pour fonctionner
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://restcountries.com/v3.1/capital/"+pays))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            return "Error";
        }
        JsonNode json = new ObjectMapper().readTree(response.body());
        String name = json.get(0).get("name").get("common").asText();
        String nameOfficial = json.get(0).get("name").get("official").asText();
        String capital = json.get(0).get("capital").get(0).asText();
        String region = json.get(0).get("region").asText();
        String subregion = json.get(0).get("subregion").asText();
        String area = json.get(0).get("area").asText();
        String population = json.get(0).get("population").asText();
        String flag = json.get(0).get("flags").get("png").asText();
        String continents = json.get(0).get("continents").get(0).asText();
        
        return "Country name = "+ name 
                + "<br>Official name = " + nameOfficial 
                + "<br> Capital =" + capital 
                + "<br> Region = " + region 
                + "<br> Subregion = " + subregion 
                + "<br> Area = " + area 
                + "<br> Population = " + population 
                + "<br> Continents = " + continents
                + "<br> <img src="+ flag+">" +
                "<form action='/rateCountry' method='get'>" +
                "        <input type='hidden' name='name' value='name'>" +
                "        <input type='text' name='email' placeholder='Votre email'>" +
                "            <select name='rate'>" +
                "                <option value='1'>1</option>" +
                "                <option value='2'>2</option>" +
                "                <option value='3'>3</option>" +
                "                <option value='4'>4</option>" +
                "                <option value='5'>5</option>" +
                "            </select>" +
                "         <input type='submit' value='Rate'>" +
                "</form>";

    }




}
