package com.example.demo.implementations;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;
import com.example.demo.repos.RoleRepository;
import com.example.demo.repos.UserRepository;
import com.example.demo.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserServiceImp implements UserService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public UserServiceImp(UserRepository userRepo, RoleRepository roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("SAVING NEW USER");
        return userRepo.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        AppUser user = userRepo.findByEmail(email);
        AppRole role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }
}
