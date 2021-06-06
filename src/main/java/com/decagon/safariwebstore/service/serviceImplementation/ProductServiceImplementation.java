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
import com.decagon.safariwebstore.utils.MethodUtils;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImplementation implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Cacheable(cacheNames = "products", sync = true)
    public Page<Product> getAllProducts(ProductPage productPage) {

        Pageable pageable = MethodUtils.getPageable(productPage);

        return productRepository.findAll(pageable);
    }

    @Override
    @Cacheable(cacheNames = "productsCategory", sync = true)
    public Page<Product> getProductsByCategory(ProductPage productPage, String categoryName) {

        Category category = getCategory(categoryName);

        Pageable pageable = MethodUtils.getPageable(productPage);

        return productRepository.findAllByCategory(category, pageable);
    }

    @Override
    @Cacheable(cacheNames = "productsSubCategory", sync = true)
    public Page<Product> getProductsByCategoryAndSubCategory(ProductPage productPage,
                                                             String categoryName,
                                                             String subCategoryName) {

        Category category = getCategory(categoryName);

        SubCategory subCategory = subCategoryRepository
                .findByNameAndCategory(subCategoryName, category).orElseThrow(
                        () -> {
                            throw new ResourceNotFoundException("Sub-Category not found!");
                        });

        Pageable pageable = MethodUtils.getPageable(productPage);

        return productRepository.findAllByCategoryAndSubCategory(category, subCategory, pageable);
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(
                () -> {
                    throw  new ResourceNotFoundException("Category not found!");
                });
    }
}
