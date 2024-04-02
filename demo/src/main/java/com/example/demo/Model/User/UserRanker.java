package com.example.demo.Model.User;

import com.example.demo.Repositories.RateRepositoryInterface;

public abstract class UserRanker extends User implements Ranker{

    public UserRanker(RateRepositoryInterface rP) {
        super(rP);
    }

    @Override
    public void rank() {
        // TODO Auto-generated method stub
    }
}
