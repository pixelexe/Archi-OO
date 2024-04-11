package com.example.demo;

import com.example.demo.Api.Api;
import com.example.demo.Api.ApiCountryByCapital;
import com.example.demo.Api.ApiCountryByCountryName;
import com.example.demo.Api.ApiRegion;
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

    
}
