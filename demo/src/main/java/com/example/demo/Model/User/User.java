package com.example.demo.Model.User;

import com.example.demo.Model.Place.Place;

import com.example.demo.Model.Rate;
import com.example.demo.Repositories.RateRepositoryInterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import java.sql.SQLException;
import java.util.List;

@Setter
@Getter
@Component
public abstract class User implements Search, Rater {

    @Autowired
    private RateRepositoryInterface rateRepository;

    private int id;

    private String userName;

    private String email;

    private String password;

    private int rateStrength;

    @Override
    public List<Rate> search(Place place) throws SQLException {
        return this.rateRepository.getAllRates(place);
    }

    @Override
    public void rate(Place place, int rating) throws SQLException {
        Rate rate = new Rate(place.getName(), this.id, rating);
        this.rateRepository.persistRate(rate);
    }

}