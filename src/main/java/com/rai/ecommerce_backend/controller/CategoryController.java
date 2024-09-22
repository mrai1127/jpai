package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public String createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return "success";
    }

    @GetMapping()
    public List<Category> listCategory(){
        return categoryService.findAll();
    }
}
