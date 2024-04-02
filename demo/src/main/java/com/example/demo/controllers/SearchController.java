package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.*;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SearchController {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/aoo";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    @GetMapping("/searchCountry")
    public String searchCountry(@RequestParam("name") String pays) throws JsonMappingException, JsonProcessingException { //Besoin que ce soit un @RestController pour fonctionner
        HttpResponse<String> response = null;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://restcountries.com/v3.1/capital/paris"))
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            try {
                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            return "Error";
        }
        JsonNode json = new ObjectMapper().readTree(response.body());
        
        return json.get(0).get("name").get("common").asText();
    }

    @GetMapping("/processForm")
    public String processForm(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {

        String test = firstName + " " + lastName;

        return test;
    }

    @GetMapping("/processFormV2")
    public String processFormV2(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        String test = firstName + " " + lastName;
        // HttpSession session = request.getSession();
        // session.setAttribute("firstName", firstName);
        // session.setAttribute("lastName", lastName);
        return test;
    }

    @GetMapping("/createAccountIndex")
    public RedirectView createAccount() {
        return new RedirectView("connexion.html");
    }

    @GetMapping("/createAccount")
    public String createAccount(@RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("email") String email) {

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO user (username, password, email) VALUES (?,?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();

                return "Account created successfully";
            }
        } catch (SQLException e) {
            System.out.println("Error inserting request into the database" + e.getMessage());
        }

        return "Rip account";
    }

    @GetMapping("/connectAccount")
    public String connexion(@RequestParam("username") String username, @RequestParam("password") String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ? AND active = 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return "Welcome " + username;
                    } else {
                        return "Invalid username or password";
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error selecting request from the database" + e.getMessage());
        }

        return "Rip connexion";
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("username") String username, @RequestParam("password") String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String deleteQuery = "UPDATE user set active = ? WHERE username = ? AND password = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();

                return "Account deleted successfully";
            }
        } catch (SQLException e) {
            System.out.println("Error deleting request from the database" + e.getMessage());
        }

        return "Rip delete";
    }

    @GetMapping("/readAccount")
    public String readAccount(@RequestParam("username") String username, @RequestParam("password") String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ? AND active = 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Username: ").append(resultSet.getString("username")).append("<br>");
                        sb.append("Email: ").append(resultSet.getString("email")).append("<br>");
                        return "welcome <br>" + sb.toString();

                    } else {
                        return "Invalid username or password";
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error selecting request from the database" + e.getMessage());
        }

        return "Rip connexion";
    }

    @GetMapping("/updateAccount")
    public String updateAccount(@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("newUsername") String newUsername,
            @RequestParam("newEmail") String newEmail) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String updateQuery = "UPDATE user set username = ?, password = ?, email = ? WHERE username = ? AND password = ? AND active = 1";
            String selectQuery = "SELECT * FROM user WHERE username = ? AND password = ? AND active = 1";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.next()) {
                        return "Invalid username or password";
                    }
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, newUsername);
                preparedStatement.setString(2, newPassword);
                preparedStatement.setString(3, newEmail);
                preparedStatement.setString(4, username);
                preparedStatement.setString(5, password);
                preparedStatement.executeUpdate();

                return "Account updated successfully";
            }
        } catch (SQLException e) {
            System.out.println("Error updating request from the database" + e.getMessage());
        }

        return "Rip update";
    }

}
