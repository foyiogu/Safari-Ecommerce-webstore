package com.decagon.safariwebstore.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "colors")
@Data
public class Color extends BaseModel {

    private String color;

    @Column(name = "product_id")
    private Long productId;

}
