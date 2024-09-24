package com.rai.ecommerce_backend.service;

import com.rai.ecommerce_backend.entity.AuthenticationToken;
import com.rai.ecommerce_backend.entity.User;
import com.rai.ecommerce_backend.exception.AuthenticationFailException;
import com.rai.ecommerce_backend.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public User getUser(String token){
        AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(token)){
            return null;
        }
        //token is not null
        return authenticationToken.getUser();
    }
    public void authenticate(String token) throws AuthenticationFailException{
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("token not valid");
        }
    }
}
