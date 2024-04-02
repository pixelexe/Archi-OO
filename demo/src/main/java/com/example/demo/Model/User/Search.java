package com.example.demo.Model.User;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.Rate;

import java.sql.SQLException;
import java.util.List;

public interface Search {
    public List<Rate> search(Place place) throws SQLException;
}
