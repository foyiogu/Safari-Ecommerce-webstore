package com.decagon.safariwebstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")

public class Order extends BaseModel {

    private String quantity;

    private String status;

    @Column(columnDefinition = "decimal")
    private double price;

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
