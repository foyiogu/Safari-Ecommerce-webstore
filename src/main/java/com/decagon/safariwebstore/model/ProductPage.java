package com.decagon.safariwebstore.model;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ProductPage {
    private int pageNumber = 0;
    private int pageSize = 16;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";
}
