package com.example.demo.repository;

import com.example.demo.domains.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
    AppUser findByEmail(String email);
}
