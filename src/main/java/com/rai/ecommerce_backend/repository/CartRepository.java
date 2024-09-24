package com.rai.ecommerce_backend.repository;

import com.rai.ecommerce_backend.entity.Cart;
import com.rai.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
