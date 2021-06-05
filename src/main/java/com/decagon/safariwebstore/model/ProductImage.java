package com.decagon.safariwebstore.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Getter
@Setter
public class ProductImage extends BaseModel{
      ;
        private String image;
        @Column(name = "product_id")
        private Long productId;

    }
