package com.example.demo;

import com.example.demo.Repositories.RateRepository;
import com.example.demo.Repositories.RateRepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RateRepositoryInterface getRateRepository() {
        return new RateRepository();
    }
}
