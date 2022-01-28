package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.AppRole;
import com.pakindessama.commerce.domains.AppUser;
import com.pakindessama.commerce.dtos.UserDto;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public interface UserService {

    public AppUser saveUser(AppUser user);

    public AppRole saveRole(AppRole role);

    public AppRole getRole (String name);

    public void addRoleToUser(String email, String roleName);

    public UserDto getUserDto(String email);

    public AppUser getUser (String mail);

    public List<UserDto> getUsers();

    public int deleteUser(UUID id);
}
