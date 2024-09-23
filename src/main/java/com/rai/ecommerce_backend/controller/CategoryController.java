package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.common.ApiResponse;
import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "Category has been created"), HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Category> listCategory(){
        return categoryService.findAll();
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteByCategoryId(@PathVariable("categoryId") int categoryId){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.deleteByCategoryId(categoryId);
        return new ResponseEntity<>(new ApiResponse(true, "category has been deleted"), HttpStatus.OK);
    }
}
