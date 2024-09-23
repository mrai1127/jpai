package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.dto.ProductDto;
import com.rai.ecommerce_backend.entity.Category;
import com.rai.ecommerce_backend.entity.Product;
import com.rai.ecommerce_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        productRepository.save(product);

    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setDescription(product.getDescription());
        productDto.setImageURL(product.getImageURL());
        productDto.setName(product.getName());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product: allProducts){
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, int productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);

        if(!product.isPresent()){
            throw new Exception("product not present");
        }
        Product product1 = product.get();
        product1.setDescription(productDto.getDescription());
        product1.setImageURL(productDto.getImageURL());
        product1.setName(productDto.getName());
        product1.setPrice(productDto.getPrice());
        productRepository.save(product1);
    }
}
