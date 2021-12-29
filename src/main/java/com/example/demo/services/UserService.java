package com.example.demo.services;

import com.example.demo.models.AppRole;
import com.example.demo.models.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String email, String roleName);
    AppUser getUser(String email);
    List<AppUser> getUsers();
}
