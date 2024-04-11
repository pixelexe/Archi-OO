package com.example.demo;

import com.example.demo.Api.Api;
import com.example.demo.Api.ApiCountryByCapital;
import com.example.demo.Api.ApiCountryByCountryName;
import com.example.demo.Api.ApiRegion;
import com.example.demo.Handler.RaterHandler;
import com.example.demo.Repositories.Rate.RateRepository;
import com.example.demo.Repositories.Rate.RateRepositoryInterface;
import com.example.demo.Repositories.User.UserRepository;
import com.example.demo.Repositories.User.UserRepositoryInterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class Config {

    @Bean({"apiRegion"})
    public Api getApi() {
        return new ApiRegion() {
        };
    }

    @Bean({"apiCapital"})
    public Api getApiCountry() {
        return new ApiCountryByCapital();
    }

    @Bean({"apiCountry"})
    public Api getApiCountryByCountryName() {
        return new ApiCountryByCountryName();
    }

    @Bean({"rateRepositoryInterface"})
    public RateRepositoryInterface rateRepositoryInterface() {
        return new RateRepository();
    }

    @Bean({"RaterHandler"})
    public RaterHandler raterHandler() {
        return new RaterHandler();
    }

}
