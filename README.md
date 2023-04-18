# WebShop
Basic Webshop build using SpringBoot, JPA and Thymeleaf.  

School Assignment

## Shop Front  

When Customer comes to page they are met with a login page and the option to either login or create a new account before they are taken to the shop.  
Webshop has category pages and search function to find products, the option to add products to a shopping cart, and to complete a purchase. 

Using JPA and a MySql database the webshop saves Products, Customers and Orders to a database.  

For an added bonus, the webshop also sends a confirmation email to the customer upon purchase.

## Shop Administration

An administrator has the option to add new products, add inventory to existing products, and to view orders by status and update the status on orders. 

## Other Details  

To simplify testing, the functionality is divided between the view, controller,  services and the respositories. There are a few tests written for the services. 
