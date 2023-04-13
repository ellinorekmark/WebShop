package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository rep;

    public List<Product> getAll() {
        return rep.findAll();

    }

    public void updateInventory(ItemAmount itemAmount) {
        Product product = rep.findById(itemAmount.itemId).get();
        product.addInventory(itemAmount.count);
        rep.save(product);
    }
    public void removeInventory(List<OrderLine> items){
        Product product;
        for (OrderLine line : items) {
            product = rep.findById(line.getProduct().getId()).get();
            product.setInventory(product.getInventory()-line.getQuantity());
            rep.save(product);
        }

    }

    public void newProduct(Product product) {
        rep.save(product);
    }

    public Optional<Product> getById(int item) {
        return rep.findById(Long.valueOf(item));
    }

    public List<Product> getCat(Category fruit) {
        return rep.findByCategory(fruit);
    }

    public List<Product> search(String search) {

        return rep.findByName(search);
    }
}


