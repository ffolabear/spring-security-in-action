package org.example.ssiach17ex5.repository;

import java.util.List;
import org.example.ssiach17ex5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("select p from Product p "
            + "where p.name like %:text% and "
            + "p.owner=?#{authentication.name}")
    List<Product> findProductsByNameContains(String text);

}
