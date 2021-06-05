package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String state;

    private String city;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_shipping_address")
    private boolean toDefaultShippingAddress;

}
