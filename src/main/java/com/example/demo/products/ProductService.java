package com.example.demo.products;


import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll () {
        return productRepository.findAll();
    }

    public  Product addNewProduct (Product product) {
        productRepository.save(product);
        return product;
    }
}
