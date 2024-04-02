package com.example.demo.Repositories;

import java.sql.SQLException;

public interface UserRepositoryInterface extends RepositoryInterface{

    void saveUser(int idUser, String username, String password, String email) throws SQLException;

    void archiveUser(int idUser) throws SQLException;

    void modifyUsername(int idUser, String username) throws SQLException;

    String getEmailById(int idUser) throws SQLException;

    String getUsernameById(int idUser) throws SQLException;


}
