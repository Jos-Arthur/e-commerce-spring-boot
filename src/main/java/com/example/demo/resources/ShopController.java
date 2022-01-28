package com.example.demo.resources;

import com.example.demo.domains.ConfirmationToken;
import com.example.demo.domains.Shop;
import com.example.demo.dtos.ShopDto;
import com.example.demo.repository.TokenRepository;
import com.example.demo.services.MailVerificationService;
import com.example.demo.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/shops")
@RequiredArgsConstructor
@Slf4j
public class ShopController {
    private final ShopService shopService;
    private final TokenRepository confirmationTokenRepository;
    private final MailVerificationService mailVerificationService;

    @PostMapping
    public ResponseEntity<Shop> add(@RequestBody Shop shop){
        shopService.addShop(shop);
        mailVerificationService.sendShopVerificationMail(shop);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping
    public ResponseEntity<List<ShopDto>> getShops () {
        return ResponseEntity.ok().body(shopService.getShops());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ShopDto> getShop (@PathVariable UUID id) {
        return ResponseEntity.ok().body(shopService.getShopDto(id));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> deleteShop(@PathVariable UUID id){
        shopService.deleteShop(id);
        return ResponseEntity.ok().body("Item deleted successfully");
    }

    @GetMapping(path = {"/status/{id}"})
    public ResponseEntity<String> disableShop (@PathVariable UUID id) {
        Shop shop = shopService.disableShop(id);
        return ResponseEntity.ok().body(shop.getIsEnabled()==false?"Item disabled successfully":"Item enabled successfully");
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail (@RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null) {
            Shop shop = shopService.enableShop(token.getShop());
            confirmationTokenRepository.delete(token);
            mailVerificationService.sendShopVerifiedMail(shop);
        }
        else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body("Email verified successfully");
    }
}
