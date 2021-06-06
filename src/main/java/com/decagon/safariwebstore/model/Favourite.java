package com.decagon.safariwebstore.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "favorite_products")
public class Favourite extends BaseModel{

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;


}
