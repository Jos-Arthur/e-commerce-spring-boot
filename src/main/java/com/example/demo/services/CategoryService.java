package com.example.demo.services;

import com.example.demo.domains.Category;
import com.example.demo.domains.Shop;
import com.example.demo.dtos.CategoryDto;
import com.example.demo.dtos.ShopDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public interface CategoryService {

    public void addCategory(Category category);

    public Category updateCategory(Category category);

    public void deleteCategory(UUID id);

    public Category getCategory(UUID id);

    public List<CategoryDto> getCategories();

    public CategoryDto getCategoryDto(UUID id);

}
