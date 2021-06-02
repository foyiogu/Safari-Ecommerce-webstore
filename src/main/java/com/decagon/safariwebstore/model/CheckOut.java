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
public class CheckOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_order")
    private Date dateOrder;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_delivered")
    private Date dateDelivered;
    private boolean status;
    @Column(name = "delivery_fee", columnDefinition = "DECIMAL")
    private double deliveryFee;
    @Column(name = "payment_type")
    private String paymentType;
    @Column(name = "total_cost", columnDefinition = "DECIMAL")
    private Double totalCost;
    @OneToMany(mappedBy = "checkOut")
    private List<Order> orders;

}
