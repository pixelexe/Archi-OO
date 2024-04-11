package com.example.demo.Model.User;

import com.example.demo.Repositories.Rate.RateRepositoryInterface;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public abstract class User implements Searcher, Rater {

    @Autowired
    private RateRepositoryInterface rateRepository;

    private int id;

    private String userName;

    private String email;

    private String password;

    protected int rateStrength;

    private String role;
}