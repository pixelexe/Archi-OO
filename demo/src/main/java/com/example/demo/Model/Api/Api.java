package com.example.demo.Model.Api;

import java.net.http.HttpRequest;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface Api {
    public static final String DOMAINE = "https://restcountries.com/v3.1" ;
    
    public HttpRequest callApi(String placeName);

    public Map<String, String> getInfo(JsonNode placeName);

    public JsonNode getJson(HttpRequest request);

    public String cleanURL(String url);

}
