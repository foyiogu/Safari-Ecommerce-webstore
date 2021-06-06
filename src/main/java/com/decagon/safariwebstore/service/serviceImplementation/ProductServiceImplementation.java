package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.Category;
import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.model.SubCategory;
import com.decagon.safariwebstore.repository.CategoryRepository;
import com.decagon.safariwebstore.repository.ProductRepository;
import com.decagon.safariwebstore.repository.SubCategoryRepository;
import com.decagon.safariwebstore.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Cacheable(value = "products", sync = true)
    public Page<Product> getAllProducts(ProductPage productPage) {

        log.info("Calling the getAllProducts() method");

        Pageable pageable = getPageable(productPage);

        return productRepository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "products", sync = true)
    public Page<Product> getProductsByCategory(ProductPage productPage, String categoryName) {

        log.info("Calling the getProductsByCategory() method");

        Category category = getCategory(categoryName);

        Pageable pageable = getPageable(productPage);

        return productRepository.findAllByCategory(category, pageable);
    }

    @Override
    @Cacheable(value = "products", sync = true)
    public Page<Product> getProductsByCategoryAndSubCategory(ProductPage productPage,
                                                             String categoryName,
                                                             String subCategoryName) {

        log.info("Calling the getProductsByCategoryAndSubCategory() method");

        Category category = getCategory(categoryName);

        SubCategory subCategory = subCategoryRepository
                .findByNameAndCategory(subCategoryName, category).orElseThrow(
                        () -> {
                            throw new ResourceNotFoundException("Sub-Category not found!");
                        });

        Pageable pageable = getPageable(productPage);

        return productRepository.findAllByCategoryAndSubCategory(category, subCategory,pageable);
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(
                () -> {
                    throw  new ResourceNotFoundException("Category not found!");
                });
    }

    private Pageable getPageable(ProductPage productPage) {
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        return PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);
    }
}
