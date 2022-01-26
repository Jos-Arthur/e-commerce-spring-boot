package com.example.demo.services;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.dtos.UserDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements UserService{

    private ModelMapper modelMapper =  new ModelMapper();;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public AppRole saveRole(AppRole role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        AppUser user = userRepo.findByEmail(email);
        AppRole role = roleRepo.findByName(roleName);
        try {
            user.getRoles().add(role);
        }catch (Exception e){
            log.error(e.toString());
        }
    }

    @Override
    public UserDto getUser(String email) {
        return modelMapper.map(userRepo.findByEmail(email), UserDto.class);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepo.findAll().stream().map(UserDto::new).collect(Collectors.toList());
    }

    @Override
    public int deleteUser(Long id) {
        userRepo.deleteById(id);
        return 0;
    }
}

