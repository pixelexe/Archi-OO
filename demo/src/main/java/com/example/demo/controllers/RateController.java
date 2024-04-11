package com.example.demo.controllers;

import com.example.demo.Model.Place.Country;
import com.example.demo.Handler.RaterHandler;
import com.example.demo.Model.Rate;
import com.example.demo.Model.User.Rater;
import com.example.demo.Model.User.User;
import com.example.demo.Repositories.Rate.RateRepositoryInterface;
import com.example.demo.Repositories.User.UserRepository;
import com.example.demo.Repositories.User.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class RateController {
    private RateRepositoryInterface rateRepository;
    private RaterHandler raterHandler;

    private UserRepositoryInterface userRepository = new UserRepository(); //on sait pas pk mais on arrive pas à l injecter correctement

    public RateController(
        @Qualifier("rateRepositoryInterface") RateRepositoryInterface rateRepository, 
        @Qualifier("RaterHandler") RaterHandler raterHandler) {
        this.rateRepository = rateRepository;
        this.raterHandler = raterHandler;
    }
    @GetMapping("/rateCountry")
    public String rateCountry(@RequestParam("name") String countryName,
                              @RequestParam("rate") int rating,
                              @RequestParam("email") String emailUser) {
        try {
            User user = userRepository.getUserByEmail(emailUser);
            if (user == null || ! (user instanceof Rater)){
                return "User is not allowed to rate countries.";
            }

            Rate rate = new Rate(new Country(countryName), user, rating, user.getRateStrength());
            rateRepository.persistRate(rate, user.getRateStrength());
            raterHandler.rankup(user);
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
