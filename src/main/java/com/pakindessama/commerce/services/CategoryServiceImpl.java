package com.pakindessama.commerce.services;

import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.dtos.CategoryDto;
import com.pakindessama.commerce.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@Service
@RequiredArgsConstructor
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }

    @Override
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryDto(UUID id) {
        return modelMapper.map(categoryRepository.findById(id).get(), CategoryDto.class);
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }
}
