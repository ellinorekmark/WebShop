package com.example.webshopkomplexjavaslutuppgift;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShopOrder> orders;


    @Email
    private String email;


    public Customer() {
    }

    public Customer(String name, String address, ArrayList<ShopOrder> orders) {
        this.name = name;
        this.address = address;
        this.orders = orders;
    }

    public boolean isLoggedIn(){
        return name != null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ShopOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShopOrder> orders) {
        this.orders = orders;
    }

    public void addNewOrder(ShopOrder cart) {
        if (orders == null) {
            orders = List.of(cart);
        } else {
            orders.add(cart);
        }
    }

    public ShopOrder getLastOrder(){
        return orders.get(orders.size()-1);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
