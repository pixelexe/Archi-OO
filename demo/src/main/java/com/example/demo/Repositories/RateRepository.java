package com.example.demo.Repositories;

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
    public List<String> getRate(String locationName) throws SQLException {

            String getRatesQuery = "SELECT rate FROM rate WHERE name = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(getRatesQuery);
            preparedStatement.setString(1, locationName);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> results = new ArrayList<>();
            while(resultSet.next()){
                results.add(resultSet.getString("rate"));
            }
            return results;
    }

    @Override
    public void setRate(int idUser, String locationName, int rate) throws SQLException {

        String setRateQuery = "INSERT INTO rate (idUser, name, rate) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(setRateQuery);
        preparedStatement.setInt(1, idUser);
        preparedStatement.setString(2, locationName);
        preparedStatement.setInt(3, rate);
        preparedStatement.executeUpdate();

    }
}
