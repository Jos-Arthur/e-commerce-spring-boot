package com.example.demo.services;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.dtos.UserDto;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public interface UserService {

    public AppUser saveUser(AppUser user);

    public AppRole saveRole(AppRole role);

    public AppRole getRole (String name);

    public void addRoleToUser(String email, String roleName);

    public UserDto getUser(String email);

    public List<UserDto> getUsers();

    public int deleteUser(Long id);
}
