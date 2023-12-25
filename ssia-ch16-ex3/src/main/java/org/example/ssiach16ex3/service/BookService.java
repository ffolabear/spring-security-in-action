package org.example.ssiach16ex3.service;

import java.util.List;
import java.util.Map;
import org.example.ssiach16ex3.model.Employee;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final Map<String, Employee> records = Map.of(
            "emma", new Employee("Emmo Thompson", List.of("Karamazov Brothes"), List.of("account", "reader")),
            "natalie", new Employee("Natalie Parker", List.of("Beautiful Paris"), List.of("researcher"))
    );

    @PostAuthorize("returnObject.roles.contains('reader')")
    public Employee getBookDetails(String name) {
        return records.get(name);
    }
}
