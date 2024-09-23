package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.common.ApiResponse;
import com.rai.ecommerce_backend.dto.ProductDto;
import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.entity.Product;
import com.rai.ecommerce_backend.repository.CategoryRepository;
import com.rai.ecommerce_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if(!category.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "'category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.createProduct(productDto, category.get());
        return new ResponseEntity<>(new ApiResponse(true, "product has been created"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products =  productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //create an api to edit product
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") int productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());
        if(!category.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "'category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(new ApiResponse(true, "product has been updated"), HttpStatus.CREATED);
    }
}
