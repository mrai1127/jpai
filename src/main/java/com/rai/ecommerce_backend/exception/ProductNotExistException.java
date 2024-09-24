package com.rai.ecommerce_backend.exception;

public class ProductNotExistException extends IllegalArgumentException {
    public ProductNotExistException(String mgs) {
        super(mgs);
    }
}
