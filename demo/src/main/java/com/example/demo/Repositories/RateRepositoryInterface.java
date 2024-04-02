package com.example.demo.Repositories;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.Rate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface RateRepositoryInterface extends RepositoryInterface {

    public List<Rate> getAllRates(Place place) throws SQLException;

    public void persistRate(Rate rate, int strength) throws SQLException;

    public List<String> getTopTen() throws SQLException;
}
