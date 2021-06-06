package com.decagon.safariwebstore.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sub_categories")
public class SubCategory extends BaseModel{

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
