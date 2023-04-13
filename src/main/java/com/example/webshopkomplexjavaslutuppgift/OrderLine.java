package com.example.webshopkomplexjavaslutuppgift;

import jakarta.persistence.*;

@Entity
public class OrderLine {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Product product;
    private int quantity;

    public OrderLine() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderLine(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double getLineTotal(){
        return product.getPrice()*getQuantity();
    }
}
