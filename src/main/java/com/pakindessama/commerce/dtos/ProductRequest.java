package com.pakindessama.commerce.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
@Getter
public class ProductRequest {
    private String label;
    private Float price;
    private String description;
    private String[] photos;
    public String shop;
    public ArrayList<String> categories = new ArrayList<>();
}
