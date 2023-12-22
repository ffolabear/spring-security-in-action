package com.example.ssiach6ex1.ex1.controller;

import com.example.ssiach6ex1.ex1.service.ProductService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    public String login(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        model.addAttribute("products", productService.findAll());
        return "main";
    }

}
