package com.example.webshopkomplexjavaslutuppgift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebShopKomplexJavaSlutuppgiftApplication implements CommandLineRunner{
//
    public static void main(String[] args) {
        SpringApplication.run(WebShopKomplexJavaSlutuppgiftApplication.class, args);
    }

    @Autowired
    ProductRepository rep;
    @Override
    public void run(String... args) throws Exception {
        rep.save(new Product("Apple","Red", 5.0, 50, Category.FRUIT));
        rep.save(new Product("Pear","Green", 7.0, 50, Category.FRUIT));
        rep.save(new Product("Carrot","Organic", 8.0, 50, Category.VEGETABLE));
        rep.save(new Product("Tomato","Big", 4.0, 50, Category.VEGETABLE));
        rep.save(new Product("Muffin","Chocolate Chip", 20.0, 30, Category.OTHER));
    }

}
