package com.example.webshopkomplexjavaslutuppgift;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopRestController {

    @Autowired
    ProductService productService;

    @GetMapping("/rs/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/rs/category/{cat}")
    public ResponseEntity<List<Product>> getCategory(@PathVariable String cat) {
        try {
            return ResponseEntity.ok().body(productService.getProductsByString(cat));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/rs/search/{searchTerm}")
    public ResponseEntity<List<Product>> findProduct(@PathVariable String searchTerm) {
        List<Product> result = productService.search(searchTerm);
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @DeleteMapping("/rs/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable int id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok().body("Successfully removed.");

        } else {
            return ResponseEntity.badRequest().body("Invalid object.");
        }
    }

    @PostMapping("/rs/addProduct")
    ResponseEntity<String> addNewProduct(@Valid @RequestBody Product product, BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid product.");
        } else {
            product = productService.newProduct(product);
            return ResponseEntity.accepted().body(product.toString());
        }


    }

    @PutMapping("/rs/updateInventory/{id}/{add}")
    ResponseEntity<String> updateInventory(@PathVariable int id, @PathVariable int add) {
        if(productService.getById(id).isPresent()){
            if(add>0){
                return ResponseEntity.accepted().body("Updated: " + productService.updateInventory(id, add).toString());
            }
            else{
                return ResponseEntity.badRequest().body("Can't add negative numbers to inventory");
            }
        }
        else{
            return ResponseEntity.badRequest().body("Failed to add inventory. Product not found.");
        }
    }


}
