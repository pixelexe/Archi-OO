package com.example.demo.Api;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class ApiCountry extends ApiAbstract{

    @Override
    public Map<String, String> getInfo(JsonNode json) {
        Map<String, String> map = new HashMap<>();
        map.put("name", json.get(0).get("name").get("common").asText());
        map.put("official", json.get(0).get("name").get("official").asText());
        map.put("capital", json.get(0).get("capital").get(0).asText());
        map.put("region", json.get(0).get("region").asText());
        map.put("subregion", json.get(0).get("subregion").asText());
        map.put("area", json.get(0).get("area").asText());
        map.put("population", json.get(0).get("population").asText());
        map.put("flag", json.get(0).get("flags").get("png").asText());
        map.put("continents", json.get(0).get("continents").get(0).asText());
        return map;
    }
}
