package com.example.demo.repository;

import com.example.demo.domains.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<Shop, UUID> {
    Shop findByEmail(String email);
    Shop findByName(String name);
    Shop findByPhone(String phone);
}
