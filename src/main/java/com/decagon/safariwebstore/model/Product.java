package com.decagon.safariwebstore.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseModel {
  
    private String name;
    @Column(columnDefinition = "decimal")
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @OneToMany(mappedBy = "productId")
    private List<Size> sizes;
    @OneToMany(mappedBy = "productId")
    private List<Color> colors;
    @OneToMany(mappedBy = "productId")
    private List<ProductImage> productImages;

    @ManyToMany(mappedBy = "favouriteProducts")
    private List<User> users;

}
