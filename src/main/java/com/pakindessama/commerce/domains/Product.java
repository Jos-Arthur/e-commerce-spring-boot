package com.pakindessama.commerce.domains;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(updatable = true, nullable = false)
    private String label;

    private Float price;
    private String description;
    private String[] photos;
    private boolean isEnabled = false;
    private boolean isBlocked = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    private Collection<Category> categories = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", nullable = true)
    private Shop shop;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    public boolean getIsEnabled(){
        return this.isEnabled;
    }
}
