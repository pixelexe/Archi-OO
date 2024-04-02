package com.example.demo.controllers;

import com.example.demo.Model.Rate;
import com.example.demo.Repositories.RateRepositoryInterface;
import com.example.demo.Repositories.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class RateController {

    @Autowired
    private UserRepositoryInterface userRepository;

    @Autowired
    private RateRepositoryInterface rateRepository;

    @GetMapping("/rateCountry")
    public String rateCountry(@RequestParam("country") String countryName,
                              @RequestParam("rate") int rating,
                              @RequestParam("email") String emailUser) {
        try {
            int idUser = userRepository.getUserByEmail(emailUser).getId();
            Rate rate = new Rate(countryName, idUser, rating);
            rateRepository.persistRate(rate);
            return "Country Rated";
        } catch (SQLException e) {
            return "Exception occurred";
        }


    }
}
