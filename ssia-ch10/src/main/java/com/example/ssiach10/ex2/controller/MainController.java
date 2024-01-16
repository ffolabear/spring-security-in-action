package com.example.ssiach10.ex2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
public class MainController {

//    @GetMapping("/main")
    public String getHello() {
        return "main.html";
    }

}
