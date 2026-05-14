package org.example.b1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    public static class Product {
        private String id;
        private String name;
        private double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    @GetMapping("/hot")
    public List<Product> getHotProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("HP001", "\u00c1o thun 'Code is Life'", 199000));
        products.add(new Product("HP002", "M\u00f3c kh\u00f3a 'Bug Free'", 99000));
        return products;
    }
}
