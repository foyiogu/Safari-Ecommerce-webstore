package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String name;
    private double price;
    private String description;
    private Category category;
    private SubCategory subCategory;
    private List<Size> sizes;
    private List<Color> colors;
    private List<ProductImage> productImages;

    public ProductDTO(String name, double price, String description, Category category, SubCategory subCategory) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
    }
}
