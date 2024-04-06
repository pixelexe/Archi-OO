package com.example.demo.Model;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.User.User;

public record Rate(Place place, User user, int rate, int rateStrength) {
    public Rate {
        if (rate < 0 || rate > 5) {
            throw new IllegalArgumentException("Rate must be between 0 and 5");
        }
    }
}
