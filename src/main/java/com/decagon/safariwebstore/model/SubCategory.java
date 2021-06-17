package com.decagon.safariwebstore.model;


import lombok.*;

import javax.persistence.*;

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

//    public void setCategory(Category category) {
//        this.category = category;
//    }
}
