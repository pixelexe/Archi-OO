package com.example.demo.Model.User;

import com.example.demo.Model.Place.Place;

import java.sql.SQLException;

public interface Rater {
    public void rate(Place place, int rating) throws SQLException;
}
