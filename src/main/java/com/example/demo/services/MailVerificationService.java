package com.example.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.domains.AppUser;
import com.example.demo.domains.ConfirmationToken;
import com.example.demo.repository.TokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import java.util.Date;


@AllArgsConstructor
@Service
@Slf4j
public class MailVerificationService {
    @Autowired
    private Environment env;
    private final TokenRepository tokenrepository;
    private EmailSenderService emailSenderService;

    public void sendVerificationMail (AppUser user) {
        Algorithm algorithm = Algorithm.HMAC256("secret123467890".getBytes());
        String domain = env.getProperty("domain");
        String sender = env.getProperty("spring.mail.username");
        String confirmationToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 24*60*60*1000))
                .withIssuer(domain)
                .sign(algorithm);

        ConfirmationToken token = new ConfirmationToken(user, confirmationToken);
        tokenrepository.save(token);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Account Verification!");
        mailMessage.setFrom(sender);
        mailMessage.setText("To confirm your account, please click here : "
                +domain+"verifyEmail?token="+token.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);
    }

    public void sendVerifiedMail (AppUser user) {
        String sender = env.getProperty("spring.mail.username");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Registration Completed!");
        mailMessage.setFrom(sender);
        mailMessage.setText("Hello "+user.getFirstName()+";Your email has been verified successfully. Thanks for joining us!");
        emailSenderService.sendEmail(mailMessage);
    }
}
