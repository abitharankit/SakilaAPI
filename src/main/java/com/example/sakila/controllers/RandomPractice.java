package com.example.sakila.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomPractice {
    @GetMapping("/testing/{id}/{name}")
    public String greetButBetter(@PathVariable String name, @PathVariable Integer id) {
        return "Hello " + name +". Your user id is " + id + ".";
    }
}

