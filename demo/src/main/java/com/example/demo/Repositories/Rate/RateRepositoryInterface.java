package com.example.demo.Repositories.Rate;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.Rate;
import com.example.demo.Repositories.RepositoryInterface;

import java.sql.SQLException;
import java.util.List;

public interface RateRepositoryInterface extends RepositoryInterface {

    public List<Rate> getAllRates(Place place) throws SQLException;

    public void persistRate(Rate rate, int strength) throws SQLException;

    public double getRate(Place place) throws SQLException;

    public List<String> getTopTen() throws SQLException;
}
