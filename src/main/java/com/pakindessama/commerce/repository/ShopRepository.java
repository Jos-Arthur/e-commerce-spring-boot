package com.pakindessama.commerce.repository;

import com.pakindessama.commerce.domains.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
    Shop findByEmail(String email);
    Shop findByName(String name);
    Shop findByPhone(String phone);
}
