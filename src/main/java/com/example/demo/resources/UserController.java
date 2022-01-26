package com.example.demo.resources;

import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.dtos.UserDto;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers () {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        AppRole role = userService.getRole("ROLE_USER");
        user.getRoles().add(role);
        userService.saveUser(user);
//        userService.addRoleToUser(user.getEmail(), "ROLE_USER");
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/role/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role){
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @GetMapping(path = {"/{email}"})
    public ResponseEntity<UserDto> getUser(@PathVariable String email){
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
