package com.example.demo.Model.Api;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public class ApiRegion extends ApiAbstract{

    public String getURL(String countryName) {
        return DOMAINE + "/region/" + countryName;
    }

    @Override
    public Map<String, String> getInfo(JsonNode json) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < json.size(); i++) {
            map.put(
            json.get(i).get("name").get("common").asText(),
            json.get(i).get("flags").get("png").asText());
        }
        return map;
    }
    
}
