package com.pakindessama.commerce.repository;

import com.pakindessama.commerce.domains.Product;
import com.pakindessama.commerce.domains.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByShop(Shop shop);

    List<Product> findAllByCategories_id(UUID id);
}
