package com.example.exersise17.Controller;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/get")
    public ResponseEntity getAllCategory() {
        return ResponseEntity.status(200).body(categoryService.getAllCategory());

    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid@RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        categoryService.addCategory(category);
        return ResponseEntity.status(200).body("added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @Valid@RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean result = categoryService.updateCategory(id, category);
        if (result) {
            return ResponseEntity.status(200).body("updated successfully");
        }
        return ResponseEntity.status(400).body("update failed");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        boolean result = categoryService.deleteCategory(id);
        if (result) {
            return ResponseEntity.status(200).body("deleted successfully");
        }
        return ResponseEntity.status(400).body("delete failed");
    }

}
