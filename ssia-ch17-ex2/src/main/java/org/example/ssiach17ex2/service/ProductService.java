package org.example.ssiach17ex2.service;

import java.util.List;
import org.example.ssiach17ex2.model.Product;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @PreFilter("filterObject.Owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        return products;
    }

}
