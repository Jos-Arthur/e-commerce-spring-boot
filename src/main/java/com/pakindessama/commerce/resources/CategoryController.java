package com.pakindessama.commerce.resources;

import com.pakindessama.commerce.domains.Category;
import com.pakindessama.commerce.dtos.CategoryDto;
import com.pakindessama.commerce.services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        categoryService.addCategory(category);
        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategory(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID id){
        return ResponseEntity.ok().body(categoryService.getCategoryDto(id));
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<String> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().body("Item deleted successfully");
    }
}
