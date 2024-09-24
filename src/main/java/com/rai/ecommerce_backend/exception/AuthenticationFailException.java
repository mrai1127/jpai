package com.rai.ecommerce_backend.exception;

public class AuthenticationFailException extends IllegalArgumentException {
    public AuthenticationFailException(String mgs){
        super(mgs);
    }
}
