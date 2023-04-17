package com.example.webshopkomplexjavaslutuppgift;

import com.example.webshopkomplexjavaslutuppgift.sendingMail.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CustomerService {
    @Autowired
    CustomerRepository rep;

    @Autowired
    ProductService prodServ;

    @Autowired
    EmailService emailService;

    Customer customer;
    ShopOrder cart = new ShopOrder();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShopOrder getCart() {
        return cart;
    }

    public void setCart(ShopOrder cart) {
        this.cart = cart;
    }

    public boolean login(String username) {

        try {
            customer = rep.findByName(username).get(0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void newCustomer(Customer customer) {
        this.customer = rep.save(customer);
    }

    public Customer update(Customer customer) {
        return rep.save(customer);
    }

    public boolean CustomerOnline() {
        return customer != null;
    }

    public ShopOrder getLastOrder() {
        return customer.getLastOrder();
    }

    public void confirmOrder() {
        cart.setCustomer(customer);
        customer.addNewOrder(cart);
        customer = rep.save(customer);
        prodServ.removeInventory(cart.getLines());
        cart = new ShopOrder();



        emailService.sendSimpleMessage(customer.getEmail(),"Order confirmation: "+ getLastOrder().getId(),emailMessage(getLastOrder()));

    }

    private String emailMessage(ShopOrder lastOrder) {

        StringBuilder message = new StringBuilder();
        message.append("Thank you for your order!\n\n Order Details:\n\n");
        message.append(lastOrder.getCustomer().getName()).append("\n");
        message.append(lastOrder.getCustomer().getAddress()).append("\n");
        message.append("Order Id: ").append(lastOrder.getId()).append("\n");
        for (OrderLine line : lastOrder.getLines()) {
            message.append(line.getProduct().name).append(" - ").append(line.getQuantity())
                    .append(" --- ").append(line.getProduct().getPrice())
                    .append(" Total: " ).append(line.getLineTotal()).append("\n");
        }
        message.append("\n\nTotal: ").append(lastOrder.getTotalCost()).append(" sek.");


        return message.toString();

    }

    public void addToCart(int item, int quantity) {
        cart.addLine(new OrderLine(prodServ.getById(item).get(), quantity));
    }

    public void updateCartItem(int product, int quantity) {
        for (OrderLine line : cart.getLines()) {
            if (line.getProduct().getId() == product) {
                if (quantity == 0) {
                    cart.removeLine(line);
                    break;

                } else {
                    line.setQuantity(quantity);
                    break;
                }


            }
        }
    }
}
