package com.example.ssiach6ex1.ex1.service;

import com.example.ssiach6ex1.ex1.entities.Product;
import com.example.ssiach6ex1.ex1.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
