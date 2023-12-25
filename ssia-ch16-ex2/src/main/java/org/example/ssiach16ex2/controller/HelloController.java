package org.example.ssiach16ex2.controller;

import java.util.List;
import org.example.ssiach16ex2.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private NameService nameService;

    @GetMapping("/secret/names/{name}")
    public List<String> hello(@PathVariable String name) {
        return nameService.getSecretNames(name);
    }

}
