package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HelloController{

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("index.html");
    }
}
