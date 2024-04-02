package com.example.demo.controllers;

import com.example.demo.Model.Rate;
import com.example.demo.Model.User.Rater;
import com.example.demo.Model.User.User;
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
    public String rateCountry(@RequestParam("name") String countryName,
                              @RequestParam("rate") int rating,
                              @RequestParam("email") String emailUser) {
        try {
            User user = userRepository.getUserByEmail(emailUser);
            if (user == null || ! (user instanceof Rater)){
                return "User is not allowed to rate countries.";
            }
            int idUser = user.getId();
            Rate rate = new Rate(countryName, idUser, rating);
            rateRepository.persistRate(rate, user.getRateStrength());
            return "Country Rated";
        } catch (SQLException e) {
            return "Exception occurred : " + e.getMessage();
        }
    }

    @GetMapping("/rankPlace")
    public String rankPlace() {
        try {
            StringBuilder sb = new StringBuilder();
            for (String s : rateRepository.getTopTen()) {
                sb.append(s).append("<br>");
            }
            return sb.toString();
        } catch (SQLException e) {
            return "Exception occurred : " + e.getMessage();
        }
    }
}
