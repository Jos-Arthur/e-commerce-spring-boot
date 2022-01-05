package com.example.demo.config;

import com.example.demo.domains.AppRole;
import com.example.demo.repository.RoleRepository;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class roleConfig {
    @Bean
    CommandLineRunner commandLineRunnerRole (RoleRepository repository) {
        return args -> {
            val clientRole = new AppRole("ROLE_USER");
            val adminRole = new AppRole("ROLE_ADMIN");
            val managerRole = new AppRole("ROLE_MANAGER");
            val rootRole = new AppRole("ROLE_SUPER_MANAGER");
//            repository.save(adminRole);
//            repository.save(clientRole);
//            repository.save(managerRole);
//            repository.save(rootRole);
        };
    }
}
