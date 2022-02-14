package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.Product;
import com.pakindessama.commerce.dtos.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public interface ProductService {
    public void addProduct(Product product);

    public Product getProduct(UUID id);

    public ProductDto getProductDto(UUID id);

    public List<ProductDto> getProducts(Pageable pageable);

    public List<ProductDto> getProductsByShop(UUID shopId, Pageable pageable);

    public  List<ProductDto> getProductsByCategory(UUID categoryId, Pageable pageable);

    public void editProduct(Product product);

    public void deleteProduct(UUID id);
}
