package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public void updateCategory(int categoryId, Category category) {
        Category category1 = categoryRepository.getById(categoryId);
        category1.setCategoryName(category.getCategoryName());
        category1.setDescription(category.getDescription());
        category1.setImageUrl(category.getImageUrl());
        categoryRepository.save(category1);
    }

    public boolean findById(int categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }

    public void deleteByCategoryId(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
