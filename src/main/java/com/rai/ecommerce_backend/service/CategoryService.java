package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
