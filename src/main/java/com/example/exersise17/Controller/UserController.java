package com.example.exersise17.Controller;

import com.example.exersise17.Model.Review;
import com.example.exersise17.Model.User;
import com.example.exersise17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@Valid@RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body("add success");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@Valid@RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
       boolean isupdate = userService.updateUser(id,user);
        if (isupdate) {
            return ResponseEntity.status(200).body("update success");
        }
        return ResponseEntity.status(400).body("update fail, not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        boolean isdelete = userService.deleteUser(id);
        if (isdelete) {
           return ResponseEntity.status(200).body("delete success");
        }
        return ResponseEntity.status(400).body("delete fail, not found");

    }
    @PutMapping("/puyProduct/{userId}/{productId}/{merchantId}/{merchantStockId}")
    public ResponseEntity buyProduct(@PathVariable int userId, @PathVariable int productId, @PathVariable int merchantId,@PathVariable int merchantStockId) {
        String isBuy = userService.buyProduct(userId, productId, merchantId,merchantStockId);
        if (isBuy.equals("true")) {
            return ResponseEntity.status(200).body("User buy product successfully");
        }
        if (isBuy.equals("1")) {
            return ResponseEntity.status(400).body("User buy product failed(user id not found)");
        }
        if (isBuy.equals("2")) {
            return ResponseEntity.status(400).body("User buy product failed(customer can buy only)");

        }
        if (isBuy.equals("3")) {
            return ResponseEntity.status(400).body("User buy product failed(product id not found)");


        }
        if (isBuy.equals("4")) {
            return ResponseEntity.status(400).body("User buy product failed(merchant Stock cannot be 0 or null)");

        }
        if (isBuy.equals("5")) {
            return ResponseEntity.status(400).body("User buy product failed(the balance must be greater than or equal the price)");

        }
        if (isBuy.equals("6")) {

            return ResponseEntity.status(400).body("User buy product failed(merchant id not found)");


        }
        return ResponseEntity.status(400).body("User buy product failed");

    }
    @PutMapping("/discount/{userId}/{productId}/{merchantId}/{merchantStockId}/{discount}")
    public ResponseEntity applyDiscount(@PathVariable Integer userId, @PathVariable int productId, @PathVariable int merchantId, @PathVariable int merchantStockId,@PathVariable float discount) {
        String isDiscount = userService.applyDiscount(userId, productId, merchantId, merchantStockId,discount);
        if (isDiscount.equals("true")) {
            return ResponseEntity.status(200).body("Admin apply discount successfully");
        }
        if (isDiscount.equals("1")) {
            return ResponseEntity.status(400).body("Admin apply discount failed(user id not found)");

        }
        if (isDiscount.equals("2")) {
            return ResponseEntity.status(400).body("Admin apply discount failed(the user must be Admin)");
        }
        if (isDiscount.equals("3")) {
            return ResponseEntity.status(400).body("Admin apply discount failed(product id not found)");

        }
        if (isDiscount.equals("4")) {
            return ResponseEntity.status(400).body("Admin apply discount failed(merchant Stock not found)");

        }
        if (isDiscount.equals("5")) {
            return ResponseEntity.status(400).body("Admin apply discount failed(discount percentage must be between 0 and 100)");

        }
        return ResponseEntity.status(400).body("Admin apply discount failed");


    }
    @PutMapping("/return/{userId}/{productId}/{merchantId}/{merchantStockId}")
    public ResponseEntity returnProduct(@PathVariable Integer userId, @PathVariable int productId, @PathVariable int merchantId,@PathVariable int merchantStockId) {
        String isReturn = userService.returnProduct(userId, productId, merchantId,merchantStockId);
        if (isReturn.equals("true")) {
            return ResponseEntity.status(200).body("Costumer return product successfully");
        }
        if (isReturn.equals("1")) {
            return ResponseEntity.status(400).body("User return product failed(user id not found)");

        }
        if (isReturn.equals("2")) {
            return ResponseEntity.status(400).body("User return product failed(customer can return only)");

        }
        if (isReturn.equals("3")) {
            return ResponseEntity.status(400).body("User buy product failed(merchant id not found)");
        }
        if (isReturn.equals("4")) {
            return ResponseEntity.status(400).body("User buy product failed(product  id not found)");

        }
        if (isReturn.equals("6")) {
            return ResponseEntity.status(400).body("User buy product failed(the product was not bought by the user)");
        }

        return ResponseEntity.status(400).body("User return product failed");


    }
    @PostMapping("/addreview")
    public ResponseEntity addReview(@RequestParam int userId, @RequestParam int productId, @RequestParam int rating, @RequestParam String comment) {
        String isAddReview = userService.addReview(userId, productId, rating, comment);
        if (isAddReview.equals("true")) {
            return ResponseEntity.status(200).body("User add review successfully");
        }
        if (isAddReview.equals("1")) {
            return ResponseEntity.status(400).body("User review failed(user not found)");
        }
        if (isAddReview.equals("2")) {
            return ResponseEntity.status(400).body("User review failed(only customer can add review)");
        }
        if (isAddReview.equals("3")) {
            return ResponseEntity.status(400).body("User review failed(Invalid rating)");
        }
        if (isAddReview.equals("4")) {
            return  ResponseEntity.status(400).body("User review failed(Product not found)");
        }
        if (isAddReview.equals("5")) {
            return  ResponseEntity.status(400).body("User review failed(User has not purchased the product)");
        }
        return ResponseEntity.status(400).body("User review failed");
    }
    @GetMapping("/getreview/{productId}")
    public ResponseEntity getReviewProduct( @PathVariable int productId) {
        List<Review> rlist = userService.getAllReviews(productId);
        if (rlist==null) {
            return ResponseEntity.status(400).body("Review not found");

        }
        return ResponseEntity.status(200).body(rlist);
    }
}
