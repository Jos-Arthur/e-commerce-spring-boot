package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.AppRole;
import com.pakindessama.commerce.domains.AppUser;
import com.pakindessama.commerce.dtos.UserDto;
import com.pakindessama.commerce.repository.RoleRepository;
import com.pakindessama.commerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImp implements UserService{

    private ModelMapper modelMapper =  new ModelMapper();
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

    public AppRole getRole (String name) {
        return roleRepo.findByName(name);
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
    public UserDto getUserDto(String email) {
        AppUser user = userRepo.findByEmail(email);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public AppUser getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<UserDto> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public int deleteUser(UUID id) {
        userRepo.deleteById(id);
        return 0;
    }

    private UserDto convertToDto(AppUser user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}

