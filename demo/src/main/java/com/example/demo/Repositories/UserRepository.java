package com.example.demo.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository implements UserRepositoryInterface {

    private final Connection conn;

    public UserRepository() {
        Connection conn1;
        try {
            conn1 = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            conn1 = null;
            e.printStackTrace();
        }
        this.conn = conn1;
    }

    @Override
    public void saveUser(int userId, String username, String password, String email) throws SQLException {
        String saveUserQuery = "INSERT INTO user (id, username, password, email) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(saveUserQuery);
        preparedStatement.setInt(1, userId);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, email);
        preparedStatement.executeUpdate();
    }

    @Override
    public void archiveUser(int idUser) throws SQLException {
        String archiveUserQuery = "UPDATE user SET isArchived = 1 WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(archiveUserQuery);
        preparedStatement.setInt(1, idUser);
        preparedStatement.executeUpdate();
    }

    @Override
    public void modifyUsername(int idUser, String username) throws SQLException {
        String modifyUserQuery = "UPDATE user SET username = ? WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(modifyUserQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setInt(2, idUser);
        preparedStatement.executeUpdate();
    }

    @Override
    public String getEmailById(int idUser) throws SQLException {
        String getEmailQuery = "SELECT email FROM user WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(getEmailQuery);
        preparedStatement.setInt(1, idUser);
        return preparedStatement.executeQuery().getString("email");
    }

    @Override
    public String getUsernameById(int idUser) throws SQLException {
        String getUsernameQuery = "SELECT username FROM user WHERE id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(getUsernameQuery);
        preparedStatement.setInt(1, idUser);
        return preparedStatement.executeQuery().getString("username");
    }
}
