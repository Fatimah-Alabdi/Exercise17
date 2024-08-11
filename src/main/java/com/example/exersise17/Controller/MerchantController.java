package com.example.exersise17.Controller;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.Merchant;
import com.example.exersise17.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchant() {
        return ResponseEntity.status(200).body(merchantService.getAllMerchant());

    }
    @PostMapping("/add")
    public ResponseEntity addMerchant (@Valid @RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body("added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable Integer id, @Valid@RequestBody Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean result = merchantService.updateMerchant(id, merchant);
        if (result) {
            return ResponseEntity.status(200).body("updated successfully");
        }
        return ResponseEntity.status(400).body("update failed");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable Integer id) {
        boolean result = merchantService.deleteMerchant(id);
        if (result) {
            return ResponseEntity.status(200).body("deleted successfully");
        }
        return ResponseEntity.status(400).body("delete failed");
    }

}
