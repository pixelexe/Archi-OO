package com.example.demo.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface RateRepositoryInterface extends RepositoryInterface {

    public List<String> getRate(String country) throws SQLException;

    public void setRate(int idUser, String country, int rate) throws SQLException;

}
