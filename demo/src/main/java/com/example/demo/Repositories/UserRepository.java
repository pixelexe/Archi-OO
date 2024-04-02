package com.example.demo.Repositories;

import com.example.demo.Model.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
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
    public void persistUser(User user) throws SQLException {
        if (this.getUserById(user.getId()) == null) {
            this.insertUser(user);
        }
        else {
            this.updateUser(user);
        }
    }

    private void insertUser(User user) throws SQLException {
        String query = "INSERT INTO users (id, username, email, password, rateStrength) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, user.getId());
        stmt.setString(2, user.getUserName());
        stmt.setString(3, user.getEmail());
        stmt.setString(4, user.getPassword());
        stmt.setInt(5, user.getRateStrength());
        stmt.executeUpdate();
    }

    private void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, user.getUserName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getId());
        stmt.executeUpdate();
    }

    @Override
    public User getUserById(int idUser) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, idUser);

        return this.formatUser(stmt.executeQuery());
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, email);

        return this.formatUser(stmt.executeQuery());
    }

    private User formatUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){

            User user = switch (resultSet.getString("role")) {
                case "GlobeTrotter" -> new GlobeTrotterUser();
                case "Experienced" -> new ExperiencedUser();
                case "Traveler" -> new TravelerUser();
                default -> new BeginnerUser();
            };

            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        else {
            return null;
        }
    }
}
