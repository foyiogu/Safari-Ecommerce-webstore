package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(ProductPage productPage) {

        return new ResponseEntity<>(productService.getAllProducts(productPage),
                HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<Page<Product>> getProductsByCategory(@PathVariable String category,
                                                               ProductPage productPage) {

        return new ResponseEntity<>(productService.getProductsByCategory(productPage, category),
                HttpStatus.OK);
    }

    @GetMapping("/{category}/{subCategory}")
    public ResponseEntity<Page<Product>> getProductsByCategoryAndSubCategory(@PathVariable String category,
                                                                             @PathVariable String subCategory,
                                                                             ProductPage productPage) {

        return new ResponseEntity<>(productService.getProductsByCategoryAndSubCategory(productPage,
                category, subCategory), HttpStatus.OK);
    }

}
