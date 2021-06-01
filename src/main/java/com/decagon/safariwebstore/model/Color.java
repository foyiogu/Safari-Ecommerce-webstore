package com.decagon.safariwebstore.model;


import javax.persistence.*;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    @Column(name = "product_id")
    private Long productId;

}
