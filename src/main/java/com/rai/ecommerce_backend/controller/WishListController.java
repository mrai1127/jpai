package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.common.ApiResponse;
import com.rai.ecommerce_backend.dto.ProductDto;
import com.rai.ecommerce_backend.entity.Product;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.entity.WishList;
import com.rai.ecommerce_backend.service.AuthenticationService;
import com.rai.ecommerce_backend.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

    //save product in wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token){
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        //save the item in wishlist
        WishList wishList = new WishList(user, product);

        wishListService.createWishList(wishList);

        return new ResponseEntity<>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);
    }

    //get all wishlist item for a user

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token){
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        List<ProductDto> wishListForUser = wishListService.getWishListForUser(user);
        return new ResponseEntity<>(wishListForUser, HttpStatus.OK);
    }
}
