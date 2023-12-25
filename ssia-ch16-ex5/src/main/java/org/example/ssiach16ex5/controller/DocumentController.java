package org.example.ssiach16ex5.controller;


import org.example.ssiach16ex5.model.Document;
import org.example.ssiach16ex5.service.DocumentService;
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
