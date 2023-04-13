package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);
}
