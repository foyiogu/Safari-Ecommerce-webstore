package com.decagon.safariwebstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sub_categories")
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory extends BaseModel{

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;
//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
