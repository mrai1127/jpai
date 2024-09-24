package com.rai.ecommerce_backend.dto.checkout;

public class CheckoutItemDto {
    private String productName;
    private int quanity;
    private double price;
    private long productId;
    private int userId;

    public CheckoutItemDto(String productName, int quanity, double price, long productId, int userId) {
        this.productName = productName;
        this.quanity = quanity;
        this.price = price;
        this.productId = productId;
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuanity() {
        return quanity;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
