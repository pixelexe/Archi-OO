package com.example.demo.controllers;

import com.example.demo.Model.User.BeginnerUser;
import com.example.demo.Model.User.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
public class UserController {

    @Autowired
    private static UserRepository userRepository;

    @GetMapping("/createAccountIndex")
    public RedirectView createAccount() {

        return new RedirectView("connexion.html");
    }

    @GetMapping("/createAccount")
    public void createAccount(@RequestParam("username") String username, @RequestParam("password") String password,
            @RequestParam("email") String email) {

        try {
            User user = new BeginnerUser();
            user.setUserName(username);
            user.setPassword(password);
            user.setEmail(email);
            userRepository.persistUser(user);

        } catch (SQLException e) {
            System.out.println("Error inserting request into the database" + e.getMessage());
        }

    }

    @GetMapping("/connectAccount")
    public String connexion(@RequestParam("email") String email, @RequestParam("password") String password) {
        String view;
        try{
            User user = userRepository.getUserByEmail(email);
            if (user != null) {
                view = userRepository.accountExistsAndActive(user) ? "Connecté" : "Erreur MDP/Email";
            } else {
                view = "Erreur MDP/Email";
            }
        }
        catch (SQLException e) {
            view = "Erreur serveur";
        }
        return view;
    }

    @GetMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            User user = userRepository.getUserByEmail(email);
            if (user != null) {
                userRepository.deleteUser(user);
                return "Account deleted successfully";
            } else {
                return "Invalid email or password";
            }
        } catch (SQLException e) {
            return "Error deleting request from the database" + e.getMessage();
        }

    }

    @GetMapping("/readAccount")
    public String readAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
        try {
            User user = userRepository.getUserByEmail(email);
            if (user != null && userRepository.accountExistsAndActive(user) && user.getPassword().equals(password))
            {
                return "Username: " + user.getUserName() + " Email: " + user.getEmail();
            } else {
                return "Invalid email or password";
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
        try {
            User user = userRepository.getUserByEmail(username);
            if (user != null && userRepository.accountExistsAndActive(user) && user.getPassword().equals(password))
            {
                user.setUserName(newUsername);
                user.setPassword(newPassword);
                user.setEmail(newEmail);
                userRepository.persistUser(user);
                return "Account updated successfully";
            } else {
                return "Invalid email or password";
            }
        } catch (SQLException e) {
            return "Error updating request from the database" + e.getMessage();
        }


    }

}
