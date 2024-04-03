package com.example.demo.Repositories;

import com.example.demo.Model.Place.Place;
import com.example.demo.Model.Rate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RateRepository implements RateRepositoryInterface{

    private final Connection conn;

    public RateRepository(){
        Connection conn1;
        try{
            conn1 = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e){
            conn1 = null;
            e.printStackTrace();
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
            ratings.add(new Rate(resultSet.getString("name"), resultSet.getInt("idUser"),
                    resultSet.getInt("rate "), resultSet.getInt("rate_strength")));
        }
        return ratings;
    }

    @Override
    public void persistRate(Rate rate, int strength) throws SQLException {

        String setRateQuery = "INSERT INTO rate (idUser, rate, name, rate_strength) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(setRateQuery);
        preparedStatement.setInt(1, rate.idUser());
        preparedStatement.setInt(2, rate.rate());
        preparedStatement.setString(3, rate.placeName());
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
