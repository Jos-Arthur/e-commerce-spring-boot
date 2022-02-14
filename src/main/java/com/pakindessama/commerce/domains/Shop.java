package com.pakindessama.commerce.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    public boolean getIsEnabled(){
        return this.isEnabled;
    }

}
