package com.decagon.safariwebstore.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseModel{

    private int quantity;

    @Column(columnDefinition = "decimal")
    private double price;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
