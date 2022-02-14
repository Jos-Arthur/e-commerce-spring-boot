package com.pakindessama.commerce.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@RequiredArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String shop;
    private ArrayList<String> roles = new ArrayList<>();
}
