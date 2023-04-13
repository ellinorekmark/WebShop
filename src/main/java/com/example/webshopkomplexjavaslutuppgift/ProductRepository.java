package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByCategory(Category c);

    List<Product> findByName(String search);
}
