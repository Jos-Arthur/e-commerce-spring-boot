package com.example.demo.dtos;

import com.example.demo.domains.AppRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
