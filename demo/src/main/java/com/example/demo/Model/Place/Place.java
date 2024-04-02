package com.example.demo.Model.Place;

import com.example.demo.Model.User.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

public abstract class Place {
    @Getter
    private double rating;

    private Map<Integer, Integer> ratings;

    @Getter
    private List<String> comments;

    @Getter
    @Setter
    private String name;

    public void addComment(User user, String comment) {
        comments.add(comment);
    }

    public void addRating(User user, int rating) {
        ratings.put(user.getRateStrength(), rating);
        this.recalculateRating();
    }

    private void recalculateRating() {
        int sum = 0;
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : ratings.entrySet()) {
            sum += entry.getKey() * entry.getValue();
            count += entry.getKey();
        }
        rating = (double) sum / count;
    }
}
