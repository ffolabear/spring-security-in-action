package com.example.ssiach12ex1.controller;

import java.util.logging.Logger;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private Logger logger = Logger.getLogger(MainController.class.getName());

    @GetMapping("/")
    public String getMain(OAuth2AuthenticationToken token) {
        logger.info(String.valueOf(token.getPrincipal()));
        return "main.html";
    }

}
