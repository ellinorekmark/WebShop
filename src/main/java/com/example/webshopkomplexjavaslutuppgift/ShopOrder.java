package com.example.webshopkomplexjavaslutuppgift;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


@Entity
public class ShopOrder {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLine> lines;

    @ManyToOne
    private Customer customer;

    OrderStatus status;

    public ShopOrder() {
        status = OrderStatus.NEW;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public ShopOrder(List<OrderLine> lines) {
        this.lines = lines;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public void addLine(OrderLine line) {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        lines.add(line);

    }

    public int getItemCount() {
        int total = 0;
        if (lines == null || lines.isEmpty()) {
            return total;
        } else {
            for (OrderLine line : lines) {
                total += line.getQuantity();
            }
            return total;
        }
    }

    public double getTotalCost() {
        int total = 0;
        if (lines == null || lines.isEmpty()) {
            return total;
        } else {

            for (OrderLine line : lines) {
                total += line.getQuantity() * line.getProduct().getPrice();
            }
            return total;
        }
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public void removeLine(OrderLine line) {
        lines.remove(line);
    }
}
