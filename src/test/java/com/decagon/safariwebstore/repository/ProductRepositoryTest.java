package com.decagon.safariwebstore.repository;

import com.decagon.safariwebstore.model.Category;
import com.decagon.safariwebstore.model.Product;
import com.decagon.safariwebstore.model.ProductPage;
import com.decagon.safariwebstore.model.SubCategory;
import com.decagon.safariwebstore.utils.MethodUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepositoryUnderTest;

    @Autowired
    private CategoryRepository categoryRepositoryUnderTest;

    @Autowired
    private SubCategoryRepository subCategoryRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        productRepositoryUnderTest.deleteAll();
        subCategoryRepositoryUnderTest.deleteAll();
        categoryRepositoryUnderTest.deleteAll();
    }

    @Test
    void shouldFindAllProductsByCategory() {
        // given
        Category clothes = new Category("clothes");
        categoryRepositoryUnderTest.save(clothes);
        Category shoes = new Category("shoes");
        categoryRepositoryUnderTest.save(shoes);
        Category accessories = new Category("accessories");
        categoryRepositoryUnderTest.save(accessories);

        SubCategory dresses = new SubCategory("dresses", clothes);
        subCategoryRepositoryUnderTest.save(dresses);
        SubCategory denim = new SubCategory("denim", clothes);
        subCategoryRepositoryUnderTest.save(denim);
        SubCategory flats = new SubCategory("flats", shoes);
        subCategoryRepositoryUnderTest.save(flats);
        SubCategory watches = new SubCategory("watches", accessories);
        subCategoryRepositoryUnderTest.save(watches);

        Product product1 = new Product("Smart watch", 132738,
                "luxury watch for men", accessories, watches);
        productRepositoryUnderTest.save(product1);
        Product product2 = new Product("Rebook flat show", 7482,
                "Cool shoe for all seasons", shoes, flats);
        productRepositoryUnderTest.save(product2);
        Product product3 = new Product("D&G jump suit", 42738,
                "Beautiful piece for ladies", clothes, dresses);
        productRepositoryUnderTest.save(product3);
        Product product4 = new Product("Skirt suit", 17938,
                "Lovely 2 piece suit for ladies", clothes, denim);
        productRepositoryUnderTest.save(product4);

        ProductPage productPage = new ProductPage();
        Pageable pageable = MethodUtils.getPageable(productPage);

        // when
        Page<Product> productsClothes = productRepositoryUnderTest.findAllByCategory(clothes, pageable);
        long expectedClothes = productsClothes.getTotalElements();

        Page<Product> productsShoes = productRepositoryUnderTest.findAllByCategory(shoes, pageable);
        long expectedShoes = productsShoes.getTotalElements();

        Page<Product> productsAccessories = productRepositoryUnderTest.findAllByCategory(accessories, pageable);
        long expectedAccessories = productsAccessories.getTotalElements();

        // then
        assertAll(
                () -> assertEquals(expectedClothes, 2L),
                () -> assertEquals(expectedShoes, 1L),
                () -> assertEquals(expectedAccessories, 1L)
        );
    }

    @Test
    void shouldFindAllByCategoryAndSubCategory() {
        // given
        Category clothes = new Category("clothes");
        categoryRepositoryUnderTest.save(clothes);
        Category shoes = new Category("shoes");
        categoryRepositoryUnderTest.save(shoes);
        Category accessories = new Category("accessories");
        categoryRepositoryUnderTest.save(accessories);

        SubCategory dresses = new SubCategory("dresses", clothes);
        subCategoryRepositoryUnderTest.save(dresses);
        SubCategory denim = new SubCategory("denim", clothes);
        subCategoryRepositoryUnderTest.save(denim);
        SubCategory flats = new SubCategory("flats", shoes);
        subCategoryRepositoryUnderTest.save(flats);
        SubCategory watches = new SubCategory("watches", accessories);
        subCategoryRepositoryUnderTest.save(watches);

        Product product1 = new Product("Smart watch", 132738,
                "luxury watch for men", accessories, watches);
        productRepositoryUnderTest.save(product1);
        Product product2 = new Product("Rebook flat show", 7482,
                "Cool shoe for all seasons", shoes, flats);
        productRepositoryUnderTest.save(product2);
        Product product3 = new Product("D&G jump suit", 42738,
                "Beautiful piece for ladies", clothes, dresses);
        productRepositoryUnderTest.save(product3);
        Product product4 = new Product("Skirt suit", 17938,
                "Lovely 2 piece suit for ladies", clothes, denim);
        productRepositoryUnderTest.save(product4);

        ProductPage productPage = new ProductPage();
        Pageable pageable = MethodUtils.getPageable(productPage);

        // when
        Page<Product> clothesDresses = productRepositoryUnderTest
                .findAllByCategoryAndSubCategory(clothes, dresses, pageable);
        long expectedClothesDresses = clothesDresses.getTotalElements();

        Page<Product> clothesDenim = productRepositoryUnderTest
                .findAllByCategoryAndSubCategory(clothes, denim, pageable);
        long expectedClothesDenim = clothesDenim.getTotalElements();

        Page<Product> shoesFlats = productRepositoryUnderTest
                .findAllByCategoryAndSubCategory(shoes, flats, pageable);
        long expectedShoesFlats = shoesFlats.getTotalElements();

        Page<Product> accessoriesWatch = productRepositoryUnderTest
                .findAllByCategoryAndSubCategory(accessories, watches, pageable);
        long expectedAccessoriesWatch = accessoriesWatch.getTotalElements();

        // then
        assertAll(
                () -> assertEquals(expectedClothesDresses, 1L),
                () -> assertEquals(expectedClothesDenim, 1L),
                () -> assertEquals(expectedShoesFlats, 1L),
                () -> assertEquals(expectedAccessoriesWatch, 1L)
        );
    }
}