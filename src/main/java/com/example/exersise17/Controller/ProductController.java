package com.example.exersise17.Controller;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.Product;
import com.example.exersise17.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct() {
        return ResponseEntity.status(200).body(productService.getAllProduct());

    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @Valid@RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean result = productService.updateProduct(id, product);
        if (result) {
            return ResponseEntity.status(200).body("updated successfully");
        }
        return ResponseEntity.status(400).body("update failed");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean result = productService.deleteProduct(id);
        if (result) {
            return ResponseEntity.status(200).body("deleted successfully");
        }
        return ResponseEntity.status(400).body("delete failed");
    }
}
