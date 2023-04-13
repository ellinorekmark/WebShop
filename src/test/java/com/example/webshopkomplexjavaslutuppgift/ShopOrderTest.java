package com.example.webshopkomplexjavaslutuppgift;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopOrderTest {

    ShopOrder cart;
    Product dummyProduct1 = new Product();
    Product dummyProduct2 = new Product();

    @BeforeEach
    void createSampleCart(){
        cart = new ShopOrder();
        dummyProduct1.setPrice(5.0);
        dummyProduct2.setPrice(2.0);
        cart.addLine(new OrderLine(dummyProduct1,5));
        cart.addLine(new OrderLine(dummyProduct2,3));
    }



    @Test
    void testCorrectItemCount(){
        assertEquals(8, cart.getItemCount());

    }

    @Test
    void testCorrectTotalCost(){
        assertEquals(31, cart.getTotalCost());
    }

}