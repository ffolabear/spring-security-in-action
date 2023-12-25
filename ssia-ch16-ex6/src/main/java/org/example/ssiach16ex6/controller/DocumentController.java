package org.example.ssiach16ex6.controller;


import org.example.ssiach16ex6.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController  {

    @Autowired
    private NameService nameService;

    @GetMapping("/hello")
    public String getDetail() {
        return "Hello, " + nameService.getName();
    }

}
