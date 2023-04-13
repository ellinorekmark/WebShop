package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class OrderService {
    @Autowired
    OrderRepository rep;

    boolean loggedIn = false;

    public List<ShopOrder> ordersBy(String status) {
        if(status.equals("All")){
            return rep.findAll();
        }
        return rep.findByStatus(OrderStatus.valueOf(status.toUpperCase()));
    }

    public void logIn(String pw){
        System.out.println("attempt to login");
        if(pw.equals("admin")){
            loggedIn = true;
            System.out.println("should be logged in");
        }
    }

    public boolean logCheck() {
        System.out.println(loggedIn);
        return loggedIn;
    }

    public void updateStatus(long orderId, String status) {
        ShopOrder order = rep.getReferenceById(orderId);
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        rep.save(order);
    }
}
