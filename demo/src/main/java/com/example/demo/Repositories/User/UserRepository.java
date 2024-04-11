package com.example.demo.Repositories.User;

import com.example.demo.Model.User.*;
import org.jetbrains.annotations.Nullable;
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
        }
        this.conn = conn1;
    }


    @Override
    public void persistUser(User user) throws SQLException {
        if (this.getUserByEmail(user.getEmail()) == null) {
            this.insertUser(user);
        }
        else {
            this.updateUser(user);
        }
    }

    private void insertUser(User user) throws SQLException {
        String query = "INSERT INTO user (username, email, password, role) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, user.getUserName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setString(4, "Beginner");
        stmt.executeUpdate();
    }

    private void updateUser(User user) throws SQLException {
        String query = "UPDATE user SET username = ?, email = ?, password = ? WHERE id = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, user.getUserName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, user.getPassword());
        stmt.setInt(4, user.getId());
        stmt.executeUpdate();
    }

    @Override
    public User getUserById(int idUser) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, idUser);

        return this.formatUser(stmt.executeQuery());
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, email);
        return this.formatUser(stmt.executeQuery());
    }

    private @Nullable User formatUser(ResultSet resultSet) throws SQLException {
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

    @Override
    public boolean accountExistsAndActive(User user) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ? AND password = ? AND active = 1";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());
        ResultSet resultSet = stmt.executeQuery();
        return resultSet.next();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String query = "UPDATE user SET active = 0 WHERE id = ?";
        PreparedStatement stmt = this.conn.prepareStatement(query);
        stmt.setInt(1, user.getId());
        stmt.executeUpdate();
    }

    @Override
    public int getNumberOfRates(User user){
        String getNumberOfRateQuery = "SELECT COUNT(*) FROM rate WHERE idUser = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(getNumberOfRateQuery);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            return 0;
        }
    }
}
