package org.example.ssiach16ex4.controller;

import org.example.ssiach16ex4.model.Document;
import org.example.ssiach16ex4.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentController  {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/document/{code}")
    public Document getDetail(@PathVariable String code) {
        return documentService.getDocument(code);
    }

}
