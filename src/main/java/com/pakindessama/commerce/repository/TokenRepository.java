package com.pakindessama.commerce.repository;

import com.pakindessama.commerce.domains.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TokenRepository extends JpaRepository<ConfirmationToken, UUID> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
