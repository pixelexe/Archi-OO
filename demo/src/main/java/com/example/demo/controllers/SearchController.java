package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/searchCountry")
    public String searchCountry(@RequestParam("name") String pays) {
        return pays + "<br> <a href='/'>Retour</a>";
    }

}
