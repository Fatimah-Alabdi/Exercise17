package com.example.exersise17.Service;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.Product;
import com.example.exersise17.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


        public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public boolean updateProduct(Integer id ,Product product) {
        Product p=productRepository.getReferenceById(id);
        if(p==null) {
            return false;
        }
        p.setName(product.getName());
        p.setPrice(product.getPrice());
        p.setCategoryID(product.getCategoryID());

        return true;
    }
    public boolean deleteProduct(Integer id) {
        Product p=productRepository.getReferenceById(id);
        if(p==null) {
            return false;
        }
       productRepository.delete(p);
        return true;
    }
}
