package org.example.ssiach17ex3.controller;

import java.util.List;
import org.example.ssiach17ex3.model.Product;
import org.example.ssiach17ex3.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/find")
    public List<Product> findProduct() {
        return productService.findProducts();
    }

}
