package com.decagon.safariwebstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "checkouts")
public class CheckOut extends BaseModel{

    @Column(name = "date_ordered")
    private Date dateOrdered;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_delivered")
    private Date dateDelivered;

    @Column(name = "delivery_fee", columnDefinition = "DECIMAL")
    private double deliveryFee;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "total_cost", columnDefinition = "DECIMAL")
    private Double totalCost;

    @OneToMany(mappedBy = "checkOut")
    private List<Order> orders;

}