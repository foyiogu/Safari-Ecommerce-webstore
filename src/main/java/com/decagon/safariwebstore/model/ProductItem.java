package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    private String name;
    private String color;
    private Integer size;
    private String image;
    private Integer quantity;
    private Double price;
}
