package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.dto.ProductDto;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.entity.WishList;
import com.rai.ecommerce_backend.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ProductService productService;

    public void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
       List<WishList> wishLists = wishListRepository.findAllByUserOrderByCreatedDateDesc(user);

       List<ProductDto> products = new ArrayList<>();

       for(WishList wishList : wishLists){
           products.add(productService.getProductDto(wishList.getProduct()));
       }
       return products;
    }
}
