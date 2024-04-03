package com.example.demo;

import com.example.demo.Model.Api.Api;
import com.example.demo.Model.Api.ApiCountryByCapital;
import com.example.demo.Model.Api.ApiCountryByCountryName;
import com.example.demo.Model.Api.ApiRegion;
import com.example.demo.Repositories.RateRepository;
import com.example.demo.Repositories.RateRepositoryInterface;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Repositories.UserRepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.example.demo")
public class Config {

    @Bean
    public RateRepositoryInterface getRateRepository() {
        return new RateRepository();
    }

    @Bean
    public UserRepositoryInterface getUserRepository() {

        return new UserRepository();
    }

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

    
}
