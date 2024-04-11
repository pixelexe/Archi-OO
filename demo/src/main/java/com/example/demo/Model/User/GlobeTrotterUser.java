package com.example.demo.Model.User;


public class GlobeTrotterUser extends User implements Ranker, Commenter {

        public GlobeTrotterUser(){
            super("GlobeTrotter");
            this.rateStrength = 4;
        }
}
