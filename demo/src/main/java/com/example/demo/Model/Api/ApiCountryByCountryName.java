package com.example.demo.Model.Api;

public class ApiCountryByCountryName extends ApiCountry{

    @Override
    public String getURL(String countryName) {
        return DOMAINE + "/name/" + countryName;
    }
    
}
