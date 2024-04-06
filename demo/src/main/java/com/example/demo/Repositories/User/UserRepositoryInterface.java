package com.example.demo.Repositories.User;

import com.example.demo.Model.User.User;
import com.example.demo.Repositories.RepositoryInterface;

import java.sql.SQLException;

public interface UserRepositoryInterface extends RepositoryInterface {

    void persistUser(User user) throws SQLException;

    User getUserById(int idUser) throws SQLException;

    User getUserByEmail(String email) throws SQLException;

    boolean accountExistsAndActive(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;

}
