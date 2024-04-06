package com.example.demo.Api;

public class ApiCountryByCountryName extends ApiCountry{

    @Override
    public String getURL(String countryName) {
        return DOMAINE + "/name/" + countryName;
    }
    
}
