package com.decagon.safariwebstore.controller;

import com.decagon.safariwebstore.dto.ProductDTO;
import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.service.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private final ModelMapper modelMapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(ProductPage productPage) {
        Page<Product> products = productService.getAllProducts(productPage);
        List<ProductDTO> productDTOList = getProductDTOList(products);
        Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOList);

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> searchProducts(@Param("keyword") String keyword) {
        Page<Product> products = productService.searchAllProducts(keyword.toLowerCase());
        List<ProductDTO> productDTOList = getProductDTOList(products);
        Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOList);

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{category}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategory(@PathVariable String category,
                                                               ProductPage productPage) {
        Page<Product> products = productService.getProductsByCategory(productPage, category);
        List<ProductDTO> productDTOList = getProductDTOList(products);
        Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOList);

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    @GetMapping("/{category}/{subCategory}")
    public ResponseEntity<Page<ProductDTO>> getProductsByCategoryAndSubCategory(@PathVariable String category,
                                                                             @PathVariable String subCategory,
                                                                             ProductPage productPage) {
        Page<Product> products = productService.
                getProductsByCategoryAndSubCategory(productPage, category, subCategory);
        List<ProductDTO> productDTOList = getProductDTOList(products);
        Page<ProductDTO> productDTOPage = new PageImpl<>(productDTOList);

        return new ResponseEntity<>(productDTOPage, HttpStatus.OK);
    }

    private List<ProductDTO> getProductDTOList(Page<Product> products) {
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

}
