package com.example.demo.Repositories;

import com.example.demo.Model.User.User;

import java.sql.SQLException;

public interface UserRepositoryInterface extends RepositoryInterface{

    void persistUser(User user) throws SQLException;

    User getUserById(int idUser) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

}
