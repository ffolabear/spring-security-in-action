package com.example.ssiach6ex1.ex1.repositories;

import com.example.ssiach6ex1.ex1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
