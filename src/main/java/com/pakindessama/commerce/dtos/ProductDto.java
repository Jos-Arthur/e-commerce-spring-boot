package com.pakindessama.commerce.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.pakindessama.commerce.domains.AppRole;
import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.domains.Shop;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
public class ProductDto {

    private UUID id;
    private String label;
    private Float price;
    private String[] images;
    private String description;
    private Collection<Category> categories = new ArrayList<>();

    private Shop shop;

    private boolean isEnabled = false;
    private boolean isBlocked = false;
}
