package com.example.exersise17.Service;

import com.example.exersise17.Model.*;
import com.example.exersise17.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final MerchantRepository merchantRepository;
    private final MerchantStockRepository merchantStockRepository;
    private final ReviewRepository reviewRepository;
    private final PurchaseRepository purchaseRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void addUser(User user) {
        userRepository.save(user);
    }
    public boolean updateUser(Integer id ,User user) {
        User u=userRepository.getReferenceById(id);
        if(u==null) {
            return false;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setBalance(user.getBalance());
        u.setRole(user.getRole());
        userRepository.save(u);
        return true;
    }
    public boolean deleteUser(Integer id) {
        User u=userRepository.getReferenceById(id);
        if(u==null) {
            return false;
        }
        userRepository.delete(u);
        return true;
    }
    public String buyProduct(Integer id, int productId, int merchantId, int merchantStockId) {
        User u=userRepository.getReferenceById(id);
        if(u==null) {
            return "1";
        }
        if(!u.getRole().equalsIgnoreCase("Customer")){
            return "2";

        }

       Product p=productRepository.getReferenceById(productId);
        if(p==null) {
            return "3";
        }

        Merchant merchant = merchantRepository.getReferenceById(merchantId);
if (merchant == null) {
    return "4";
}

        MerchantStock merchantStock = merchantStockRepository.getReferenceById(merchantStockId);
if (merchantStock == null ||merchantStock.getStuck()==0) {
    return "5";
}
if (u.getBalance()<p.getPrice()){
    return "6";
}
merchantStock.setStuck(merchantStock.getStuck()-1);
u.setBalance(u.getBalance()-p.getPrice());
userRepository.save(u);

        Purchase purchase = new Purchase();
        purchase.setUserId(id);
        purchase.setProductId(productId);
        purchaseRepository.save(purchase);


return "true";
    }
    public String applyDiscount(Integer userId, int productId, int merchantId,int merchantStockId ,float discountPercentage) {
        User u=userRepository.getReferenceById(userId);
        if(u==null) {
            return "1";
        }
        if(!u.getRole().equalsIgnoreCase("Admin")){
            return "2";
        }
        Merchant merchant = merchantRepository.getReferenceById(merchantId);
        if (merchant == null) {
            return "3";
        }
        Product product = productRepository.getReferenceById(productId);
        if (product == null) {
            return "4";
        }
        MerchantStock merchantStock= merchantStockRepository.getReferenceById(merchantStockId);
        if (merchantStock == null ) {
            return "5";
        }
        if(discountPercentage<=0||discountPercentage>100) {
            return "6";
        }
        float originalPrice = product.getPrice();
        float discountAmount = (originalPrice * discountPercentage) / 100;
        float discountedPrice = originalPrice - discountAmount;
        product.setPrice(discountedPrice);
        productRepository.save(product);
        return "true";
    }
    public String returnProduct(Integer userId, int productId, int merchantId, int merchantStockId) {
        User u=userRepository.getReferenceById(userId);
        if(u==null) {
            return "1";
        }
        if(!u.getRole().equalsIgnoreCase("Customer")){
            return "2";
        }
        Product product = productRepository.getReferenceById(productId);
        if (product == null) {
            return "3";
        }
        Merchant merchant = merchantRepository.getReferenceById(merchantId);
        if (merchant == null) {
            return "4";
        }
        MerchantStock merchantStock = merchantStockRepository.getReferenceById(merchantStockId);
        if (merchantStock == null ) {
            return "5";
        }
        List<Purchase> purchases = purchaseRepository.findAll();
        boolean hasPurchased = false;
        for (Purchase purchase : purchases) {
            if (purchase.getUserId().equals(userId) && purchase.getProductId().equals(productId)) {
                hasPurchased = true;

            }
        }

        if (!hasPurchased) {
            return "6";
        }
        merchantStock.setStuck(merchantStock.getStuck() + 1);
        u.setBalance(u.getBalance() + product.getPrice());
        userRepository.save(u);

        return "true";

    }
    public String addReview(int userId, int productId, int rating,String comment) {
        User u=userRepository.getReferenceById(userId);
        if(u==null) {
            return "1";
        }
        if(!u.getRole().equalsIgnoreCase("Customer")){
            return "2";
        }
        Product product = productRepository.getReferenceById(productId);
        if (product == null) {
            return "3";
        }
        if (rating < 1||rating > 5) {
            return "4";
        }
        List<Purchase> purchases = purchaseRepository.findAll();
        boolean hasPurchased = false;
        for (Purchase purchase : purchases) {
            if (purchase.getUserId().equals(userId) && purchase.getProductId().equals(productId)) {
                hasPurchased = true;

            }
        }

        if (!hasPurchased) {
            return "5";
        }
        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setRating(rating);
        review.setComment(comment);

        reviewRepository.save(review);

        return "true";

    }
    public List<Review> getAllReviews( int productId) {
        List<Review> reviews= reviewRepository.findAll();


        if(reviews==null) {
           return null;
       }
       return reviewRepository.findAll();



    }


    }
