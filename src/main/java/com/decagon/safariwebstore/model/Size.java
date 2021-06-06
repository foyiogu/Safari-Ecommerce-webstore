package com.decagon.safariwebstore.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_size")
@Getter
@Setter
public class Size extends BaseModel{


    private String size;

    @Column(name = "product_id")
    private Long productId;
}
