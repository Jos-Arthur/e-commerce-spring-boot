package com.example.demo.domains;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;
    private String name;
    private String address;
    @Column(unique = true)
    private String email;
    private String phone;
    private String description;
    private String logo;
    private boolean isEnabled = false;
    private boolean isBlocked = false;

    public boolean getIsEnabled(){
        return this.isEnabled;
    }
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "super_manager", referencedColumnName = "id")
    private AppUser admin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();
}
