package com.pakindessama.commerce.domains;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table
@Getter
@Setter
public class AppRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true)
    private String name;

    public AppRole(String name) {
        this.name = name;
    }
}
