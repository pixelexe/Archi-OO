package com.example.demo.Api;

public class ApiCountryByCapital extends ApiCountry{

    @Override
    public String getURL(String capital) {
        return DOMAINE + "/capital/" + capital;
    }

}
