package com.decagon.safariwebstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubCategoryDTO {

    private String name;

    private Category category;

}
