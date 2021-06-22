package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShippingAddress extends BaseModel {

    private String fullName;

    private String email;

    private String address;

    private String state;

    private String city;

    private String phone;

    private Boolean isDefaultShippingAddress;

    @OneToOne
    private Order order;
}
