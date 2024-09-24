package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.dto.cart.AddToCartDto;
import com.rai.ecommerce_backend.dto.cart.CartDto;
import com.rai.ecommerce_backend.dto.cart.CartItemDto;
import com.rai.ecommerce_backend.entity.Cart;
import com.rai.ecommerce_backend.entity.Product;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.exception.CustomException;
import com.rai.ecommerce_backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddToCartDto addToCartDto, User user) {
        //validate if the product id is valid
       Product product = productService.findById(addToCartDto.getProductId());

       Cart cart = new Cart();
       cart.setProduct(product);
       cart.setUser(user);
       cart.setQuantity(addToCartDto.getQuantity());
       cart.setCreatedDate(new Date());
        //save the cart
        cartRepository.save(cart);
    }

    public CartDto listCartItem(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        double totalCost = 0;
        for(Cart cart: cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItemDtos.add(cartItemDto);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItemDtos);
        return cartDto;
    }

    public void deleteCartItem(Integer cartItemId, User user) {

        //the item id belongs to user
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);
        if(optionalCart.isEmpty()){
            throw new CustomException("cart item id is invalid" + cartItemId);
        }
        Cart cart = optionalCart.get();
        if(cart.getUser() != user){
            throw new CustomException("cart item does not belong to user:" + cartItemId);
        }
        cartRepository.delete(cart);
    }
}
