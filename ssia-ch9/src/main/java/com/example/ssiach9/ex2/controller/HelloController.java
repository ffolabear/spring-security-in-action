package com.example.ssiach9.ex2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloController {

//    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}