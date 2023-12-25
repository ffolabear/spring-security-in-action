package org.example.ssiach16ex3.controller;

import java.util.List;
import org.example.ssiach16ex3.model.Employee;
import org.example.ssiach16ex3.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee hello(@PathVariable String name) {
        return bookService.getBookDetails(name);
    }

}
