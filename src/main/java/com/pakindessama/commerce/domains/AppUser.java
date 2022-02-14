package com.pakindessama.commerce.domains;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    private Boolean isEnabled = false;
    private Boolean isBlocked = false;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<AppRole> roles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "shop_id", nullable = true)
    private Shop shop;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();
}
