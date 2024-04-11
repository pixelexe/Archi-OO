package com.example.demo.Model.User;

public class ExperiencedUser extends User implements Commenter, Ranker {

    public ExperiencedUser(){
        super("Experienced");
        this.rateStrength = 3;
    }
}

