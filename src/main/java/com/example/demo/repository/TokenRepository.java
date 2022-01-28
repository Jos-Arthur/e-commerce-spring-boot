package com.example.demo.repository;

import com.example.demo.domains.AppUser;
import com.example.demo.domains.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<ConfirmationToken, UUID> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
