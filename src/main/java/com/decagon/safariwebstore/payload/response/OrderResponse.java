package com.decagon.safariwebstore.payload.response;

import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private String quantity;
    private String status;
    private String productName;
    private String dateOrdered;
    private String dateDelivered;
}
