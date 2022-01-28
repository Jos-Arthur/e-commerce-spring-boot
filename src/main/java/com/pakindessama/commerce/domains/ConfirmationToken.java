package com.pakindessama.commerce.domains;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, referencedColumnName = "id")
    private Shop shop;

    public ConfirmationToken(AppUser user, String token) {
        this.user = user;
        createdDate = new Date();
        confirmationToken = token;
    }

    public ConfirmationToken(Shop shop, String token) {
        this.shop = shop;
        createdDate = new Date();
        confirmationToken = token;
    }
}
