package com.example.exersise17.Controller;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.MerchantStock;
import com.example.exersise17.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantstuck")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getAllMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getAllMerchantstock());

    }
    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);

        }
       merchantStockService.addMerchantstock(merchantStock);
        return ResponseEntity.status(200).body("added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id, @Valid@RequestBody MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean result = merchantStockService.updateMerchantStock(id, merchantStock);
        if (result) {
            return ResponseEntity.status(200).body("updated successfully");
        }
        return ResponseEntity.status(400).body("update failed");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        boolean result = merchantStockService.deleteMerchantstock(id);
        if (result) {
            return ResponseEntity.status(200).body("deleted successfully");
        }
        return ResponseEntity.status(400).body("delete failed");
    }
    @PutMapping("/addstock/{mSId}/{productId}/{merchantId}/{amount}")
    public ResponseEntity addStock(@PathVariable Integer mSId,@PathVariable int productId,@PathVariable int merchantId,@PathVariable int amount){
        String isaddstock= merchantStockService.addStok(mSId,productId,merchantId,amount);
        if(isaddstock.equals("true")){
            return ResponseEntity.status(200).body(" Stock  of product is Added");

        }
        if(isaddstock.equals("1")){
        return ResponseEntity.status(400).body(" Stock Not Added(product id not found)");
    }
        if(isaddstock.equals("2")){
            return ResponseEntity.status(400).body(" Stock Not Added(merchant Id not found)");

        }
        return ResponseEntity.status(400).body("Stock Not Added");
    }

}
