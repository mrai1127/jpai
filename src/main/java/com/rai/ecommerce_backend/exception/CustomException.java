package com.rai.ecommerce_backend.exception;

public class CustomException extends IllegalArgumentException{
    public CustomException(String mgs){
        super(mgs);
    }
}
