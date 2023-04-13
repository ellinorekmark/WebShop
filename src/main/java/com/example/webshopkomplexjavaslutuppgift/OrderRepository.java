package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderRepository extends JpaRepository<ShopOrder, Long> {


    List<ShopOrder> findByStatus(OrderStatus status);
}
