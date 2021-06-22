package com.decagon.safariwebstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")

public class Order extends BaseModel {
    private String quantity;

    private String deliveryMethod;

    private Double price;

    private Date dateOrdered;

    private Double cardDiscount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDelivered;

    @Column(columnDefinition = "DECIMAL")
    private Double deliveryFee = 2000.00;

    private String paymentType;

    private String status;

    private Double costOfProducts; //alias subtotal

    private Boolean isGift;

    @Column(columnDefinition = "DECIMAL")
    private Double totalCost;

    @OneToOne
    private ShippingAddress shippingAddress;

    @OneToMany(targetEntity = CartItem.class)
    private List<CartItem> cartItems;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "users", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "checkout_id")
    private CheckOut checkOut;

}
