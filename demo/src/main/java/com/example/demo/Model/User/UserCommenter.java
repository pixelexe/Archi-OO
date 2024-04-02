package com.example.demo.Model.User;

import com.example.demo.Repositories.RateRepositoryInterface;

public abstract class UserCommenter extends UserRanker implements Commenter{
    public UserCommenter(RateRepositoryInterface rP) {
        super(rP);
    }

    @Override
    public void comment() {
        // TODO Auto-generated method stub
    }
}
