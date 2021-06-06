package com.decagon.safariwebstore.service;

import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.ProductPage;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> getAllProducts(ProductPage productPage);
    Page<Product> getProductsByCategory(ProductPage productPage, String categoryName);
    Page<Product> getProductsByCategoryAndSubCategory(ProductPage productPage,
                                                      String categoryName, String subCategoryName);
}
