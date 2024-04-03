package com.example.demo.Model;

import com.example.demo.Model.User.User;

public record Rate(String placeName, int idUser, int rate, int rateStrength) {
    public Rate {
        if (rate < 0 || rate > 5) {
            throw new IllegalArgumentException("Rate must be between 0 and 5");
        }
    }
}
