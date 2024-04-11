package com.example.demo.Handler;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Model.User.User;
import com.example.demo.Repositories.User.UserRepositoryInterface;

public class RaterHandler {
    
    @Autowired
    private UserRepositoryInterface userRepository;

    public void rankup(User user) throws SQLException{

        int numberOfRate = userRepository.getNumberOfRates(user);
        System.out.println("Number of rates : " + numberOfRate);
        if(numberOfRate <= 5){
            user.setRole("Beginner");
        } else if(numberOfRate <= 10){
            user.setRole("Traveler");
        } else if(numberOfRate <= 15){
            user.setRole("Experienced");
        } else {
            user.setRole("GlobeTrotter");
        }
        userRepository.persistUser(user);
    }
}
