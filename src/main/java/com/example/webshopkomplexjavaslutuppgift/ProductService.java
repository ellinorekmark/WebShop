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

    public Product updateInventory(long id, int amount) {
        Product product = rep.findById(id).get();
        product.addInventory(amount);
        return rep.save(product);
    }

    public void removeInventory(List<OrderLine> items) {
        Product product;
        for (OrderLine line : items) {
            product = rep.findById(line.getProduct().getId()).get();
            product.setInventory(product.getInventory() - line.getQuantity());
            rep.save(product);
        }

    }

    public Product newProduct(Product product) {
        return rep.save(product);
    }

    public Optional<Product> getById(int item) {
        return rep.findById(Long.valueOf(item));
    }

    public List<Product> getCat(Category cat) {
        return rep.findByCategory(cat);
    }

    public List<Product> search(String search) {

        return rep.findByName(search);
    }

    public List<Product> getProductsByString(String productCategory) throws Exception {

        if(productCategory.equalsIgnoreCase("All")){
            return getAll();
        }
        else{
            try {
                return getCat(Category.valueOf(productCategory.toUpperCase()));
            }
            catch (Exception e){
                throw new Exception("Invalid category.");
            }
        }
    }

    public boolean deleteProduct(long id) {
        Optional<Product> p = rep.findById(id);
        if(p.isPresent()){
            rep.delete(p.get());
            return true;
        }
        else{
            return false;
        }

    }
}


