package com.example.demo.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.dtos.UserDto;
import com.example.demo.services.MailVerificationService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
