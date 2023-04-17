package com.example.webshopkomplexjavaslutuppgift;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
public class ShopController {
    @Autowired
    ProductService service;
    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;



    @GetMapping("/newProduct")
    String getNewProduct(Model m) {
        m.addAttribute("product", new Product());
        m.addAttribute("categories", List.of(Category.values()));
        return "newProduct";
    }

    @PostMapping("/newProduct")
    String postNewProduct(Model m, @Valid Product product, BindingResult br) {

        m.addAttribute("product", new Product());
        m.addAttribute("categories", List.of(Category.values()));
        if(br.hasErrors()){
            m.addAttribute("message", "Problem adding product.");
            return "newProduct";
        }

        service.newProduct(product);

        m.addAttribute("message", "Product successfully added.");

        return "newProduct";
    }

    @GetMapping("/restock")
    String getRestockPage(Model m) {

        m.addAttribute("productlist", service.getAll());

        m.addAttribute("update", new ItemAmount());
        return "inventory";
    }

    @PostMapping("/restock")
    String postRestockPage(Model m, @ModelAttribute("update") ItemAmount itemAmount) {

        service.updateInventory(itemAmount);
        m.addAttribute("update", new ItemAmount());
        m.addAttribute("productlist", service.getAll());
        m.addAttribute("message", "Inventory updated.");

        return "inventory";
    }

    @GetMapping("/shop")
    String getShopFront(Model m) {

        if (customerService.CustomerOnline()) {
            m.addAttribute("productlist", service.getAll());
            m.addAttribute("cart", customerService.getCart());
            m.addAttribute("user", customerService.getCustomer());
            return "webshop";
        } else {
            m.addAttribute("user", new Customer());
            return "loginPage";
        }
    }

    @GetMapping("/fruits")
    String fruits(Model m) {
        m.addAttribute("productlist", service.getCat(Category.FRUIT));
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        return "webshop";
    }

    @GetMapping("/vegetables")
    String vegetables(Model m) {
        m.addAttribute("productlist", service.getCat(Category.VEGETABLE));
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        return "webshop";
    }

    @GetMapping("/other")
    String other(Model m) {
        m.addAttribute("productlist", service.getCat(Category.OTHER));
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        return "webshop";
    }


    @PostMapping("/addToCart")
    String addToCart(Model m, @RequestParam int item, @RequestParam int quantity) {

        customerService.addToCart(item, quantity);

        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        m.addAttribute("productlist", service.getAll());

        return "webshop";
    }

    @GetMapping("/showCart")
    String getShoppingCartView(Model m) {
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        return "showCart";
    }

    @PostMapping("/updateCart")
    String updateCart(Model m, @RequestParam int product, @RequestParam int quantity) {
        customerService.updateCartItem(product, quantity);
        m.addAttribute("message", "Cart was updated.");
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        return "showCart";

    }

    @PostMapping("/login")
    String login(Model m, @RequestParam String username) {
        if(customerService.login(username)){
            m.addAttribute("productlist", service.getAll());
            m.addAttribute("cart", customerService.getCart());
            m.addAttribute("user", customerService.getCustomer());
            m.addAttribute("orderLine", new OrderLine());
            return "webshop";
        }
        else{
            m.addAttribute("message","Invalid User.");
            m.addAttribute("user", new Customer());
            return"loginPage";
        }




    }

    @PostMapping("/newUser")
    String newUser(Model m, @Valid Customer customer, BindingResult br) {
        if (br.hasErrors()) {
            m.addAttribute("message", "Failed to create user. Please try again.");
            m.addAttribute("user", new Customer());
            return "loginPage";
        }
        customerService.newCustomer(customer);
        m.addAttribute("productlist", service.getAll());
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        m.addAttribute("orderLine", new OrderLine());

        return "webshop";
    }

    @GetMapping("/confirm")
    String confirmOrder(Model m) {
        customerService.confirmOrder();

        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        m.addAttribute("order", customerService.getLastOrder());


        return "thankyou";
    }


    @PostMapping("/search")
    String search(Model m, @RequestParam("searchTerm") String search ){
        System.out.println(search);
        m.addAttribute("message", "Search results for "+ search);
        m.addAttribute("cart", customerService.getCart());
        m.addAttribute("user", customerService.getCustomer());
        m.addAttribute("orderLine", new OrderLine());
        m.addAttribute("productlist", service.search(search));
        return "webshop";
    }

    @GetMapping("/admin")
    String loginAdmin(Model m){
        if(orderService.loggedIn){
            m.addAttribute("statusList", List.of(OrderStatus.values()));
            m.addAttribute("orders", orderService.ordersBy("All"));
            return "admin";
        }
        else{
            return "adminLogin";
        }


    }
    @PostMapping("/filterStatus")
    String filterStatus(Model m, @RequestParam("statusSelect") String status ){
        System.out.println(status);
        m.addAttribute("statusList", List.of(OrderStatus.values()));
        m.addAttribute("orders", orderService.ordersBy(status));
        return "admin";
    }

    @PostMapping("/adminLogin")
    String adminPage(Model m, @RequestParam("password")String pw){
        System.out.println(pw);
        orderService.logIn(pw);
        if(orderService.logCheck()){
            m.addAttribute("statusList", List.of(OrderStatus.values()));
            m.addAttribute("orders", orderService.ordersBy("All"));
            return "admin";
        }

        else {
            return "adminLogin";
        }
    }

    @PostMapping("/updateOrderStatus")
    String updateOrderStatus(Model m, @RequestParam("order") long order,@RequestParam("orderStatus") String status ){
        orderService.updateStatus(order, status);
        m.addAttribute("statusList", List.of(OrderStatus.values()));
        m.addAttribute("orders", orderService.ordersBy("All"));
        return "admin";
    }




}
