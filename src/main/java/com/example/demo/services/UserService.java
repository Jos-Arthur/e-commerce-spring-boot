package com.example.demo.services;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.dtos.UserDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserService(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public AppUser saveUser(AppUser user) {
        log.info("SAVING NEW USER");
        return userRepo.save(user);
    }

    public AppRole saveRole(AppRole role) {
        return roleRepo.save(role);
    }

    public void addRoleToUser(String email, String roleName) {
        AppUser user = userRepo.findByEmail(email);
        AppRole role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    public AppUser getUser(String email) {
        return userRepo.findByEmail(email);
    }

    public List<UserDto> getUsers() {
        return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }
}
