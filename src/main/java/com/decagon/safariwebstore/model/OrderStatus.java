package com.decagon.safariwebstore.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    DELIVERED("Order has been delivered"),
    CANCELLED("Order has been canclled"),
    PENDING("Awaiting delivery"),
    REFUNDED("Refund made");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
