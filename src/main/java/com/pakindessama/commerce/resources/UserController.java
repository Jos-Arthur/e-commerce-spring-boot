package com.pakindessama.commerce.resources;

import com.pakindessama.commerce.domains.AppRole;
import com.pakindessama.commerce.domains.AppUser;
import com.pakindessama.commerce.dtos.UserDto;
import com.pakindessama.commerce.services.MailVerificationService;
import com.pakindessama.commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final MailVerificationService mailVerificationService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers () {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser user){
        AppRole role = userService.getRole("ROLE_USER");
        AppRole role2 = userService.getRole("ROLE_ADMIN");

        user.getRoles().add(role);
        user.getRoles().add(role2);

        userService.saveUser(user);
        mailVerificationService.sendUserVerificationMail(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/role/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole role){
        return ResponseEntity.ok().body(userService.saveRole(role));
    }

    @GetMapping(path = {"/{email}"})
    public ResponseEntity<UserDto> getUser(@PathVariable String email){
        return ResponseEntity.ok().body(userService.getUserDto(email));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
