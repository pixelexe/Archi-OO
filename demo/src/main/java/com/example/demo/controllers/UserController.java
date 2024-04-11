package com.example.demo.controllers;

import com.example.demo.Model.User.BeginnerUser;
import com.example.demo.Model.User.User;
import com.example.demo.Repositories.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import java.sql.SQLException;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

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
            view = e.getMessage();
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
            @RequestParam("newUsername") String newUsername) {
        try {
            User user = userRepository.getUserByEmail(username);
            if (user != null && userRepository.accountExistsAndActive(user) && user.getPassword().equals(password))
            {
                user.setUserName(newUsername);
                user.setPassword(newPassword);
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
