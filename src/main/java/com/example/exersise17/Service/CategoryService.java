package com.example.exersise17.Service;

import com.example.exersise17.Model.Category;
import com.example.exersise17.Model.User;
import com.example.exersise17.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
    public void addCategory(Category  category) {
        categoryRepository.save(category);
    }
    public boolean updateCategory(Integer id ,Category category) {
        Category c=categoryRepository.getReferenceById(id);
        if(c==null) {
            return false;
        }
        c.setName(category.getName());

        return true;
    }
    public boolean deleteCategory(Integer id) {
        Category c=categoryRepository.getReferenceById(id);
        if(c==null) {
            return false;
        }
        categoryRepository.delete(c);
        return true;
    }
}
