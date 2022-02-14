package com.pakindessama.commerce.resources;

import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.domains.Product;
import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.ProductDto;
import com.pakindessama.commerce.dtos.ProductRequest;
import com.pakindessama.commerce.services.CategoryService;
import com.pakindessama.commerce.services.ProductService;
import com.pakindessama.commerce.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) throws Exception{

        Product product = new Product();
        try {
            Shop shop = shopService.getShop(UUID.fromString(productRequest.getShop()));
            product.setShop(shop);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        product.setLabel(productRequest.getLabel());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        ArrayList<String> categories = productRequest.getCategories();
        for(String cat:categories){
            try {
                Category category = categoryService.getCategory(UUID.fromString(cat));
                if(category == null) continue;
                product.getCategories().add(category);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        productService.addProduct(product);
        return ResponseEntity.ok().body("Item added successfully");
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(Pageable pageable){
        return ResponseEntity.ok().body(productService.getProducts(pageable));
    }

    @GetMapping(path = {"/getByShop/{id}"})
    public ResponseEntity<List<ProductDto>> getProductsByShop(@PathVariable UUID id, Pageable pageable){
        return ResponseEntity.ok().body(productService.getProductsByShop(id, pageable));
    }

    @GetMapping(path = {"/getByCategory/{id}"})
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable UUID id, Pageable pageable){
        return ResponseEntity.ok().body(productService.getProductsByCategory(id, pageable));
    }
}


//a51992de-fae1-4854-9a87-8a77294ead48