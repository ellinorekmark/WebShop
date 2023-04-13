package com.example.webshopkomplexjavaslutuppgift;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {
    Product dummyProduct = new Product();



    @Test
    void testShouldAddProductToCart(){

        int id = 2;
        CustomerService c = new CustomerService();
        c.prodServ = mock(ProductService.class);
        when(c.prodServ.getById(id)).thenReturn(Optional.of(dummyProduct));
        assert(c.cart.getLines() == null);
        c.addToCart(id, 5);
        assert (!(c.cart.getLines().isEmpty()));
        assertEquals(dummyProduct, c.cart.getLines().get(0).getProduct());
    }

    @Test
    void testShouldRemoveProductInCart(){
        CustomerService c = new CustomerService();
        c.prodServ = mock(ProductService.class);
        int id = 2;
        dummyProduct.setId(id);
        when(c.prodServ.getById(id)).thenReturn(Optional.of(dummyProduct));
        c.addToCart(id,3);
        assert(c.cart.getLines().get(0).getProduct().equals(dummyProduct));
        c.updateCartItem(id,0);
        assert(c.cart.getLines().isEmpty());


    }

}