package com.example.demo.Repositories.Rate;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.Rate;
import com.example.demo.Repositories.User.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RateRepository implements RateRepositoryInterface {

    private final Connection conn;

    @Autowired
    private UserRepositoryInterface userRepo;

    public RateRepository() {
        Connection conn1;
        try{
            conn1 = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e){
            conn1 = null;
        }
        this.conn = conn1;
    }

    @Override
    public List<Rate> getAllRates(Place place) throws SQLException {

        String getRatesQuery = "SELECT rate FROM rate WHERE name = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(getRatesQuery);
        preparedStatement.setString(1, place.getName());
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Rate> ratings = new ArrayList<>();
        while (resultSet.next()) {
            ratings.add(new Rate(place, userRepo.getUserById(resultSet.getInt("idUser")),
                    resultSet.getInt("rate "), resultSet.getInt("rate_strength")));
        }
        return ratings;
    }

    @Override
    public double getRate(Place place) throws SQLException {
        String getRateQuery = "SELECT CAST(SUM(rate * rate_strength) AS FLOAT) / CAST(SUM(rate_strength) AS FLOAT) AS avg_rate FROM rate WHERE name = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(getRateQuery);
        preparedStatement.setString(1, place.getName());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        return resultSet.getDouble("avg_rate");
    }

    @Override
    public void persistRate(Rate rate, int strength) throws SQLException {

        String setRateQuery = "INSERT INTO rate (idUser, rate, name, rate_strength) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(setRateQuery);
            preparedStatement.setInt(1, rate.user().getId());
            preparedStatement.setInt(2, rate.rate());
            preparedStatement.setString(3, rate.place().getName());
            preparedStatement.setInt(4, strength);
            preparedStatement.executeUpdate();

    }



    public List<String> getTopTen() throws SQLException {
        String getTopTenQuery = "SELECT name,  CAST(SUM(rate * rate_strength) AS FLOAT) / CAST(SUM(rate_strength) AS FLOAT) AS avg_rate FROM rate GROUP BY name ORDER BY avg_rate DESC LIMIT 10";

        PreparedStatement preparedStatement = conn.prepareStatement(getTopTenQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> topTen = new ArrayList<>();
        while (resultSet.next()) {
            topTen.add("Place: " + resultSet.getString("name") + " | Average Rate: " + resultSet.getFloat("avg_rate"));
        }
        return topTen;
    }

    

}
