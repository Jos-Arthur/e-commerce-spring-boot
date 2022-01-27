package com.example.demo.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.domains.AppRole;
import com.example.demo.domains.AppUser;
import com.example.demo.domains.ConfirmationToken;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.MailVerificationService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@Slf4j
@AllArgsConstructor
public class TokenController {

    private final UserService userService;
    private TokenRepository confirmationTokenRepository;
    private UserRepository userRepository;
    private final MailVerificationService mailVerificationService;


    @GetMapping("/api/refreshToken")
    public void resfrehToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("REFRESH TOKEN");
        String authorizationheader = request.getHeader(AUTHORIZATION);
        if(authorizationheader != null && authorizationheader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationheader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret123467890".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodeJWT = verifier.verify(refresh_token);
                String username = decodeJWT.getSubject();
                AppUser user = userService.getUser(username);

                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(AppRole::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            }catch (Exception exception){
                log.error("Error, {}", exception.getMessage());
                response.setHeader("Error", exception.getMessage());
                //response.sendError(FORBIDDEN.value());

                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else{
            throw new RuntimeException("");
        }
    }
    @GetMapping("/api/verifyEmail")
    public ResponseEntity<String> verifyEmail (@RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null) {
            AppUser user = userRepository.findByEmail(token.getUser().getEmail());
            user.setIsEnabled(true);
            userRepository.save(user);
            confirmationTokenRepository.delete(token);
            mailVerificationService.sendVerifiedMail(user);
        }
        else {
            return ResponseEntity.ok().body("Invalid Email");
        }
        return ResponseEntity.ok().body("Email verified successfully");
    }

}
