package com.decagon.safariwebstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String state;
    private String location;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "is_shipping_address")
    private boolean isShippingAddress;

}
