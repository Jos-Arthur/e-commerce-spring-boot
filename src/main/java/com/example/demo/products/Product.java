package com.example.demo.products;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String label;
    private String description;
    private Float price;
    private LocalDate createdAt;
    private LocalDate updateAt;


    public Product() {
    }

    public Product(Long id, String label, String description, Float price, List<String> image, LocalDate createdAt, LocalDate updateAt) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.price = price;
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Product(String label, String description, Float price) {
        this.label = label;
        this.description = description;
        this.price = price;
        this.createdAt = LocalDate.now();
        this.updateAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdateAt() {
        return updateAt;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = LocalDate.now();
    }

    public void setUpdateAt(LocalDate updateAt) {
        this.updateAt = LocalDate.now();
    }
}
