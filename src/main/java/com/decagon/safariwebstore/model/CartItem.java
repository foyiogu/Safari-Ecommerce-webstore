package com.decagon.safariwebstore.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quantity;

    @Column(columnDefinition = "decimal")
    private double price;
    @ManyToOne
    @JoinColumn(name = "produt_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
