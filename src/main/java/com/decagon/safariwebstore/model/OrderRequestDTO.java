package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private String deliveryMethod;

    private Date dateOrdered;

    private Date dateDelivered;

    private Double deliveryFee;

    private String paymentType;

    private Double costOfProducts;

    private Double cardDiscount;

    private Boolean isGift;

    private Double totalCost;

    private ShippingAddress shippingAddress;

    private List<Long> cartItems;

}
