package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.Category;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.model.SubCategory;
import com.decagon.safariwebstore.repository.CategoryRepository;
import com.decagon.safariwebstore.repository.ProductRepository;
import com.decagon.safariwebstore.repository.SubCategoryRepository;
import com.decagon.safariwebstore.utils.MethodUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {

    private ProductPage productPage;

    @Mock private ProductRepository productRepository;

    @Mock private CategoryRepository categoryRepository;

    @Mock private SubCategoryRepository subCategoryRepository;

    private ProductServiceImplementation productServiceUnderTest;

    @BeforeEach
    void setUp() {
        productPage = new ProductPage();
        productServiceUnderTest = new ProductServiceImplementation(productRepository,
                categoryRepository, subCategoryRepository);
    }

    @Test
    void canGetAllProducts() {
        // given
        Pageable pageable = MethodUtils.getPageable(productPage);

        // when
        productServiceUnderTest.getAllProducts(productPage);

        // then
        verify(productRepository).findAll(pageable);
    }

    @Test
    void canGetProductsByCategory() {
        // given
        Category clothes = new Category("clothes");
        Pageable pageable = MethodUtils.getPageable(productPage);
        given(categoryRepository.findByName("clothes")).willReturn(java.util.Optional.of(clothes));

        // when
        productServiceUnderTest.getProductsByCategory(productPage, "clothes");

        // then
        verify(productRepository).findAllByCategory(clothes, pageable);

    }

    @Test
    void willThrowWhenCategoryIsNotFound1() {
        // given
        Category clothes = new Category("clothes");
        Pageable pageable = MethodUtils.getPageable(productPage);

        // when
        // then
        assertThatThrownBy(() -> productServiceUnderTest.getProductsByCategory(productPage, "clothes"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Category not found!");

        verify(productRepository, never()).findAllByCategory(clothes, pageable);
    }

    @Test
    void canGetProductsByCategoryAndSubCategory() {
        // given
        Category clothes = new Category("clothes");
        SubCategory dresses = new SubCategory("dresses", clothes);
        Pageable pageable = MethodUtils.getPageable(productPage);
        given(categoryRepository.findByName("clothes")).willReturn(java.util.Optional.of(clothes));
        given(subCategoryRepository.findByNameAndCategory("dresses", clothes))
                .willReturn(java.util.Optional.of(dresses));

        // when
        productServiceUnderTest.getProductsByCategoryAndSubCategory(productPage,
                "clothes", "dresses");

        // then
        verify(productRepository).findAllByCategoryAndSubCategory(clothes, dresses, pageable);
    }

    @Test
    void willThrowWhenCategoryIsNotFound2() {
        // given
        Category clothes = new Category("clothes");
        SubCategory dresses = new SubCategory("dresses", clothes);
        Pageable pageable = MethodUtils.getPageable(productPage);

        // when
        // then
        assertThatThrownBy(() -> productServiceUnderTest.getProductsByCategoryAndSubCategory(productPage,
                "clothes", "dresses"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Category not found!");

        verify(productRepository, never()).findAllByCategoryAndSubCategory(clothes, dresses, pageable);
    }

    @Test
    void willThrowWhenSubCategoryIsNotFound() {
        // given
        Category clothes = new Category("clothes");
        SubCategory dresses = new SubCategory("dresses", clothes);
        Pageable pageable = MethodUtils.getPageable(productPage);
        given(categoryRepository.findByName("clothes")).willReturn(java.util.Optional.of(clothes));

        // when
        // then
        assertThatThrownBy(() -> productServiceUnderTest.getProductsByCategoryAndSubCategory(productPage,
                "clothes", "dresses"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Sub-Category not found!");

        verify(productRepository, never()).findAllByCategoryAndSubCategory(clothes, dresses, pageable);
    }

}