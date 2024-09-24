package com.rai.ecommerce_backend.controller;

import com.rai.ecommerce_backend.common.ApiResponse;
import com.rai.ecommerce_backend.dto.cart.AddToCartDto;
import com.rai.ecommerce_backend.dto.cart.CartDto;
import com.rai.ecommerce_backend.entity.Product;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.service.AuthenticationService;
import com.rai.ecommerce_backend.service.CartService;
import com.rai.ecommerce_backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    //post cart api
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("token") String token){

        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    //get all cart items for a user

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token")String token){
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        //get cart items
        CartDto cartDto = cartService.listCartItem(user);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    //delete a cart item for a user
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItemById(@PathVariable("cartItemId") Integer cartItemId,
                                                          @RequestParam("token")String token){
        //authenticate the token
        authenticationService.authenticate(token);

        //find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(cartItemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "Deleted from cart"), HttpStatus.OK);

    }
}
