package com.example.demo.controllers;
import com.example.demo.Api.Api;
import com.example.demo.Model.Place.Country;
import com.example.demo.Repositories.Rate.RateRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Map;

@RestController
public class SearchController {
    private Api apiCapital;
    private Api apiCountry;
    private Api apiRegion;

    @Autowired
    private RateRepositoryInterface rateRepository;

    public SearchController(@Qualifier("apiCapital") Api apiCapital, @Qualifier("apiCountry") Api apiCountry,
            @Qualifier("apiRegion") Api apiRegion) {
        this.apiCapital = apiCapital;
        this.apiCountry = apiCountry;
        this.apiRegion = apiRegion;
    }

    @GetMapping("/searchByCapital")
    public String searchCapital(@RequestParam("name") String pays) throws SQLException {
        try {
            double rate = rateRepository.getRate(new Country(pays));
            Map<String, String> map = apiCapital.getInfo(apiCapital.getJson(apiCapital.callApi(pays)));
            return "Country name = " + map.get("name")
                    + "<br>Official name = " + map.get("official")
                    + "<br> Capital =" + map.get("capital")
                    + "<br> Region = " + map.get("region")
                    +
                    "<br> Subregion = " + map.get("subregion")
                    + "<br> Area = " + map.get("area")
                    + "<br> Population = " + map.get("population")
                    + "<br> Continents = " + map.get("continents")
                    + "<br> <img src=" + map.get("flag") + ">" +
                    "<br> <br> <hr> <h1>Rate:" + rateRepository.getRate(new Country(map.get("name"))) + "/5 <br> Rate this country </h1> <br>" +
                    "<br>"+
                    rate + "<img src='https://imgs.search.brave.com/6RXgVm0dUwMlbrmIntvX0RqtYD1n8sypsvq7QvtHuRQ/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/YmFsbGFqYWNrLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAx/Ni8xMS9ldG9pbGUt/amF1bmUuanBn' width=20px height=20px>"
                    +
                    "<form action='/rateCountry' method='get'>" +
                    "        <input type='hidden' name='name' value='" + map.get("name") + "'>" +
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
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @GetMapping("/searchByCountry")
    public String searchCountry(@RequestParam("name") String pays) {
        try {
            double rate = rateRepository.getRate(new Country(pays));
            Map<String, String> map = apiCountry.getInfo(apiCountry.getJson(apiCountry.callApi(pays)));
            return "Country name = " + map.get("name")
                    + "<br>Official name = " + map.get("official")
                    + "<br> Capital =" + map.get("capital")
                    + "<br> Region = " + map.get("region")
                    +
                    "<br> Subregion = " + map.get("subregion")
                    + "<br> Area = " + map.get("area")
                    + "<br> Population = " + map.get("population")
                    + "<br> Continents = " + map.get("continents")
                    + "<br> <img src=" + map.get("flag") + ">" +
                    "<br>"+
                    rate + "<img src='https://imgs.search.brave.com/6RXgVm0dUwMlbrmIntvX0RqtYD1n8sypsvq7QvtHuRQ/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/YmFsbGFqYWNrLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAx/Ni8xMS9ldG9pbGUt/amF1bmUuanBn' width=20px height=20px>"
                    +
                    "<form action='/rateCountry' method='get'>" +
                    "        <input type='hidden' name='name' value='" + map.get("name") + "'>" +
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
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/searchByRegion")
    public String searchByRegion(@RequestParam("region") String region) { 
        try {
            
            Map<String, String> map = apiRegion.getInfo(apiRegion.getJson(apiRegion.callApi(region)));
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                double rate = rateRepository.getRate(new Country(entry.getKey()));
                sb.append(
                        entry.getKey()
                    + "<br> <form action='searchByCountry'>"
                    + "<input type='hidden' name='name' value='"+ entry.getKey() + "'>"
                    + "<button type='submit' value='More info'>"
                    + "<img src=" + entry.getValue() + ">" + "</button>"
                    + "</form>"+
                    rate + "<img src='https://imgs.search.brave.com/6RXgVm0dUwMlbrmIntvX0RqtYD1n8sypsvq7QvtHuRQ/rs:fit:860:0:0/g:ce/aHR0cHM6Ly93d3cu/YmFsbGFqYWNrLmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAx/Ni8xMS9ldG9pbGUt/amF1bmUuanBn' width=20px height=20px>"
                    + "<br> <br>");
            }
            return sb.toString();
        }catch(

    Exception e)
    {
        return e.getMessage();
    }
}

}
