package com.pakindessama.commerce.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ShopDto {

    private UUID id;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String description;
    private String logo;
    private boolean isEnabled = false;
    private boolean isBlocked = false;
}
