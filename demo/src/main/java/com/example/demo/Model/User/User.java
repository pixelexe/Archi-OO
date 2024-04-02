package com.example.demo.Model.User;

import com.example.demo.Model.Place;

import com.example.demo.Repositories.RateRepository;
import com.example.demo.Repositories.RateRepositoryInterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Setter
@Getter
@Component
public abstract class User implements Search, Rater {

    private final RateRepositoryInterface rateRepository;

    private int id;

    private String userName;

    private String email;

    private String password;

    private int rateStrength;

    @Autowired
    public User(RateRepositoryInterface rP){
        this.rateRepository = rP;
    }

    @Override
    public List<String> search(String placeName) throws SQLException {
        return this.rateRepository.getRate(placeName);
    }

    @Override
    public void rate(Place place, int rating) throws SQLException {
        this.rateRepository.setRate(this.id, place.getName(), rating);
    }

}