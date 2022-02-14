package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.dtos.CategoryDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public interface CategoryService {

    public void addCategory(Category category);

    public Category updateCategory(Category category);

    public void deleteCategory(UUID id);

    public Category getCategory(UUID id);

    public Category getCategoryByName(String name);

    public List<CategoryDto> getCategories();

    public CategoryDto getCategoryDto(UUID id);

}
