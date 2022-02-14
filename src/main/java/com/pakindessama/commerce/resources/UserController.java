package com.pakindessama.commerce.resources;

import com.pakindessama.commerce.domains.AppRole;
import com.pakindessama.commerce.domains.AppUser;
import com.pakindessama.commerce.domains.Shop;
import com.pakindessama.commerce.dtos.UserDto;
import com.pakindessama.commerce.dtos.UserRequest;
import com.pakindessama.commerce.services.MailVerificationService;
import com.pakindessama.commerce.services.ShopService;
import com.pakindessama.commerce.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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
    private final ShopService shopService;
    private final MailVerificationService mailVerificationService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers (Pageable pageable) {
        return ResponseEntity.ok().body(userService.getUsers(pageable));
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUser> saveUser(@RequestBody UserRequest userRequest){
        AppUser user = new AppUser();
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        if(userRequest.getShop() != null){
            Shop shop = shopService.getShop(UUID.fromString(userRequest.getShop()));
            if(shop != null)
                user.setShop(shop);
        }
        ArrayList<String> roles = userRequest.getRoles();
        for(String rol:roles){
            AppRole role = userService.getRole(rol);
            if(role == null) continue;
            user.getRoles().add(role);
        }
        AppRole role = userService.getRole("ROLE_USER");
        user.getRoles().add(role);
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
        log.info(id.toString());
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}

