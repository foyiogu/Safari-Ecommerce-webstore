package com.decagon.safariwebstore.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDTO {
    private String deliveryMethod;

    private Date dateOrdered;

    private Double cardDiscount;

    private Date dateDelivered;

    private Double deliveryFee;

    private String paymentType;

    private Double costOfProducts; //alias subtotal

    private Double totalCost;

    private ShippingAddress shippingAddress;

    List<ProductItem> products;


}
