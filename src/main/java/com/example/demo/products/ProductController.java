package com.example.demo.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll () {
        return productService.findAll();
    }

    @PostMapping
    public Product create (@RequestBody Product product) {
        System.out.println(product);
        productService.addNewProduct(product);
        return product;
    }
}
