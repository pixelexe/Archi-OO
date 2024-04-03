package com.example.demo.Model.Place;

import com.example.demo.Model.Rate;
import com.example.demo.Model.User.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public abstract class Place {

    @Getter
    private double rating;

    private List<Rate> ratings;

    @Getter
    private List<String> comments;

    @Getter
    @Setter
    private String name;

    public void addComment(User user, String comment) {
        comments.add(comment);
    }

    public void addRate(Rate rate) {
        ratings.add(rate);
        this.recalculateRating();
    }

    public void addRate(List<Rate> rates) {
        ratings.addAll(rates);
        this.recalculateRating();
    }

    private void recalculateRating() {
        double numerator = 0;
        double denominator = 0;
        for (Rate rate : ratings) {
            numerator += rate.rate() * rate.rateStrength();
            denominator += rate.rateStrength();
        }
        this.rating = numerator / denominator;
    }
}
