package com.decagon.safariwebstore.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseModel {
  
    private String name;
    @Column(columnDefinition = "decimal")

    //TODO type should be String
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Category> category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SubCategory> subCategory;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Size> sizes;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Color> colors;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ProductImage> productImages;

    @ManyToMany(mappedBy = "favouriteProducts")
    private List<User> users;

    public void setName(String name) {
        this.name = name.toLowerCase();
    }
}
