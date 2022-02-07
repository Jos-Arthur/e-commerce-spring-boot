package com.pakindessama.commerce.resources;

import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.domains.Product;
import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.ProductDto;
import com.pakindessama.commerce.services.CategoryService;
import com.pakindessama.commerce.services.ProductService;
import com.pakindessama.commerce.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final ShopService shopService;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        Shop shop = shopService.getShop(UUID.fromString("1735934a-0c7c-40c0-b69c-e06a3287db57"));
        Category category = categoryService.getCategory(UUID.fromString("2c881f3a-aad0-4917-ad7b-2f792697b8f3"));
        product.setShop(shop);
        product.getCategories().add(category);
        productService.addProduct(product);
        return ResponseEntity.ok().body("Item added successfully");
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(Pageable pageable){
        return ResponseEntity.ok().body(productService.getProducts(pageable));
    }

    @GetMapping(path = {"/getByShop/{id}"})
    public ResponseEntity<List<ProductDto>> getProductsByShop(@PathVariable UUID id){
        return ResponseEntity.ok().body(productService.getProductsByShop(id));
    }

    @GetMapping(path = {"/getByCategory/{id}"})
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable UUID id){
        return ResponseEntity.ok().body(productService.getProductsByCategory(id));
    }
}
