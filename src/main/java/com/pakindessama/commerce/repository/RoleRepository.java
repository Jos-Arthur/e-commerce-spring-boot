package com.pakindessama.commerce.repository;

import com.pakindessama.commerce.domains.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<AppRole, UUID> {
    AppRole findByName(String name);
}
