package com.example.demo.Model.User;

public class TravelerUser extends User implements Ranker {
        public TravelerUser(){
            super("Traveler");
            this.rateStrength = 2;
        }
}
