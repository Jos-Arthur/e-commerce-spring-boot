package com.example.demo.repository;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    AppRole findByName(String name);
}
