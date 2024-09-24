package com.rai.ecommerce_backend.repository;

import com.rai.ecommerce_backend.entity.AuthenticationToken;
import com.rai.ecommerce_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
