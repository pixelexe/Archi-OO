package com.example.demo.Repositories.Rate;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.User.User;
import com.example.demo.Model.Rate;
import com.example.demo.Repositories.RepositoryInterface;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface RateRepositoryInterface extends RepositoryInterface {

    public List<Rate> getAllRates(Place place) throws SQLException;

    public void persistRate(Rate rate, int strength) throws SQLException;

    public double getRate(Place place) throws SQLException;

    public List<String> getTopTen() throws SQLException;

    public void rankup(User user) throws SQLException;

    public int getNumberOfRate(User user) throws SQLException;
}
