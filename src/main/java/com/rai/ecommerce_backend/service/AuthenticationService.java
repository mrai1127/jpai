package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.entity.AuthenticationToken;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }
}
